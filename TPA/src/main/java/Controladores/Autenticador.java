package Controladores;

import java.net.HttpURLConnection;
import java.util.LinkedList;
import java.util.List;
import Exceptions.NingunaSesionAbiertaException;
import Exceptions.UsuarioNoExisteException;
import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import Usuario.Usuario;
import spark.Request;
import spark.Response;

public class Autenticador
{	
	private RepoUsuariosDB repoUsuarios = new RepoUsuariosDB();
	private List<Long> usuariosLogueados = new LinkedList<>();
	private String USER_ID = "uid"; //Cookie que "persiste" al usuario
	private String LOGIN_URI = "/";

	public static Autenticador instance = new Autenticador();
	
	public static Autenticador getInstance(){
		return instance;
	}
	
	public void guardarCredenciales(Request request, Usuario usuario)	{
		request.session().attribute(USER_ID, usuario.getId());
		usuariosLogueados.add(usuario.getId());
	}
	
	public void quitarCredenciales(Request request, Response response){
		Usuario usuario = reconocerUsuario(request, response);
		usuariosLogueados.remove(usuario.getId());
		request.session().removeAttribute(USER_ID);
    }
	
	public Usuario reconocerUsuario(Request pedido, Response respuesta){
		Usuario usuario = null;
		Long id = pedido.session().attribute(USER_ID);
		
		try{
			validarSesionEnCurso(pedido);
			usuario = getUsuario(id);
		}
		
		catch(NingunaSesionAbiertaException | UsuarioNoExisteException e){
			respuesta.status(HttpURLConnection.HTTP_PROXY_AUTH);
			respuesta.cookie("mensaje","Para acceder al contenido, primero debe identificarse");
			respuesta.redirect(LOGIN_URI);
		}
		
		return usuario;
	}
	
	private Usuario getUsuario(Long id){
		return repoUsuarios.getAll().stream()
    			.filter(u->u.getId().equals(id))
    			.findAny()
    			.orElseThrow(UsuarioNoExisteException::new);
    }
    
    private void validarSesionEnCurso(Request pedido){
    	if(!sesionEnCurso(pedido)) throw new NingunaSesionAbiertaException();
    }
    
    public boolean sesionEnCurso(Request request){
    	Long id = request.session().attribute(USER_ID);
    	return  id != null && usuariosLogueados.stream().anyMatch(idLogueado->idLogueado.equals(id));
    }
}
