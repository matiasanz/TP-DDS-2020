package Controladores;

import java.util.LinkedList;
import java.util.List;
import Exceptions.NingunaSesionAbiertaException;
import Exceptions.UsuarioNoExisteException;
import Usuario.Usuario;
import spark.Request;
import spark.Response;

public class Autenticador
{
	private List<Usuario> usuariosLogueados = new LinkedList<>();
	String USER_ID = "uid"; //Cookie que "persiste" al usuario
	String LOGIN_URI = "/";
	
	public void guardarCredenciales(Request request, Usuario usuario)	{
		request.session().attribute(USER_ID, usuario.getId());
		usuariosLogueados.add(usuario);
	}
	
	public void quitarCredenciales(Request request, Response response){
		Usuario usuario = reconocerUsuario(request, response);
		usuariosLogueados.remove(usuario);

		request.session().removeAttribute(USER_ID);
    }
	
	//Metodo pensado para una vez que el usuario se autentico, reconocerlo por un id, que quedaria guardado en una cookie
	public Usuario reconocerUsuario(Request pedido, Response respuesta){
		Usuario usuario = null;
		Long id = pedido.session().attribute(USER_ID);
		
		try{
			validarSesionEnCurso(pedido);
			usuario = getUsuarioLogueado(id);
		}
		
		catch(NingunaSesionAbiertaException | UsuarioNoExisteException e){
			respuesta.redirect(LOGIN_URI);
		}
		
		return usuario;
	}
	
	private Usuario getUsuarioLogueado(Long id){
    	Usuario usuario = usuariosLogueados.stream()
    			.filter(u->u.getId().equals(id))
    			.findAny()
    			.orElseThrow(UsuarioNoExisteException::new);
    	
    	return usuario;
    }
    
    private void validarSesionEnCurso(Request pedido){
    	if(!sesionEnCurso(pedido)) throw new NingunaSesionAbiertaException();
    }
    
    public boolean sesionEnCurso(Request request){
    	Long id = request.session().attribute(USER_ID);
    	return  id != null && usuariosLogueados.stream().anyMatch(usuario->usuario.getId().equals(id));
    }
}
