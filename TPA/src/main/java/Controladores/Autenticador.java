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
	private String USER_ID = "uid"; //Cookie que "persiste" al usuario
	private String LOGIN_URI = "/";

	public static Autenticador instance = new Autenticador();
	
	public void guardarCredenciales(Request request, Usuario usuario)	{
		request.session().attribute(USER_ID, usuario.getId());
	}
	
	public void quitarCredenciales(Request request, Response response){
		request.session().removeAttribute(USER_ID);
    }
	
	public void reautenticar(Request pedido, Response respuesta){
		Long id = pedido.session().attribute(USER_ID);
		
		try{
			validarSesionEnCurso(pedido);
			repoUsuarios.getUsuario(id);
		}
		
		catch(NingunaSesionAbiertaException | UsuarioNoExisteException e){
			respuesta.status(HttpURLConnection.HTTP_PROXY_AUTH);
			respuesta.cookie("mensaje","Para acceder al contenido, primero debe identificarse");
			respuesta.redirect(LOGIN_URI);
		}
	}
		
	public Usuario getUsuario(Request request, Response response){
		Long id = request.session().attribute(USER_ID);
		return repoUsuarios.getUsuario(id);
    }
    
    private void validarSesionEnCurso(Request pedido){
    	if(!sesionEnCurso(pedido)) throw new NingunaSesionAbiertaException();
    }
    
    public boolean sesionEnCurso(Request request){
    	Long id = request.session().attribute(USER_ID);
    	return  id != null && repoUsuarios.getUsuario(id)!= null;
    }
}
