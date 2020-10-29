package Controladores;

import java.util.Map;
import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import Repositorios.RepositorioDeUsuarios.UsuarioNoExisteException;
import Usuario.ErrorDeAutenticacionException;
import Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController
{	
	private RepoUsuariosDB repoUsuarios = new RepoUsuariosDB();
	private int ERROR_CREDENCIALES = 401;
	private String USER_ID = "uid";
	
	public ModelAndView getLoginPage(Request pedido, Response respuesta){
		return getLoginPage(pedido, respuesta, "");
	}
	
    public ModelAndView getLoginPage(Request pedido, Response respuesta, String mensaje) {
	    try{    
	    	iniciarSesion(pedido,respuesta);
	    	respuesta.redirect("/menu");	
	    	return null;
	    }
			
	    catch(UsuarioNoExisteException | ErrorDeAutenticacionException e) {
	    	respuesta.status(ERROR_CREDENCIALES);
			return new HomeController().getHome(pedido, respuesta,
					"El usuario y/o la contraseña ingresada son incorrectos");
		}
    }
    
    public void iniciarSesion(Request request, Response response){
    	Map<String,String> body = Controllers.getBody(request);
    
       	Usuario usuario = repoUsuarios.getByUsername( body.get("username") );
       	usuario.autenticar( body.get("password") );    	
       	
       	response.cookie(USER_ID,   (usuario.getId().toString())   ); //Agregar operacion para no pasarle el id del usuario, agregarle algun logaritmo, algo biyectivo.
    }
    
    public ModelAndView logout(Response response){
    	response.removeCookie(USER_ID);
    	response.redirect("/");
    	return null;
    }
}
