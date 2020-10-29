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
	    	String mensajeDeError = "El usuario y/o la contraseña ingresada son incorrectos";
			return new HomeController().getHome(respuesta, 400, mensajeDeError);
		}
    }
       
    public void iniciarSesion(Request request, Response response){
    	Map<String,String> body = Controllers.getBody(request);
    
       	Usuario usuario = repoUsuarios.getByUsername( body.get("username") );
       	usuario.autenticar( body.get("password") );    	
       	
       	response.cookie("uid",   (usuario.getId().toString())   ); //Agregar operacion para no pasarle el id del usuario, agregarle algun logaritmo, algo biyectivo.
    }
}
