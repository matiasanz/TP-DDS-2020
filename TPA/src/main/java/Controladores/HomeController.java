package Controladores;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Exceptions.ErrorDeAutenticacionException;
import Exceptions.UsuarioNoExisteException;
import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import Usuario.Usuario;

public class HomeController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	private final String ARCHIVO_LOGIN = "login.html.hbs";
	private RepoUsuariosDB repoUsuarios = new RepoUsuariosDB();
	private Autenticador autenticador = Autenticador.getInstance();
	private final String MENSAJE_TOKEN = "mensaje";
	
	//Pagina de inicio de sesion
    public ModelAndView getHome(Request request, Response response) {	        					
        if(autenticador.sesionEnCurso(request)){
        	response.redirect("/menu");
        }

    	return new ModelAndView( generarModelo(request, response) , ARCHIVO_LOGIN);
    }
    
    private Map<String, Object> generarModelo(Request pedido, Response respuesta){
        Map<String, Object> modelo = new HashMap<>();
		
        String mensaje = pedido.cookie(MENSAJE_TOKEN);
        if(mensaje!=null){
        	modelo.put(MENSAJE_TOKEN, mensaje);
        	respuesta.removeCookie(MENSAJE_TOKEN);        	
        }
		
		return modelo;
    }
	
    public ModelAndView tryLogin(Request pedido, Response respuesta) {
	    try{    
	    	iniciarSesion(pedido,respuesta);
	    	respuesta.status(HttpURLConnection.HTTP_ACCEPTED);
	    	respuesta.redirect("/menu");	
	    
	    } catch(UsuarioNoExisteException | ErrorDeAutenticacionException e) {
	    	respuesta.status(HttpURLConnection.HTTP_PROXY_AUTH);
			respuesta.cookie(MENSAJE_TOKEN, "**El usuario y/o la contraseña ingresada son incorrectos");
			respuesta.redirect("/");
		}
	    
	    return null;
    }
    
    public void iniciarSesion(Request request, Response response){
       	Usuario usuario = repoUsuarios.getUsuario(request.queryParams("username"));
       	usuario.autenticar(request.queryParams("password"));
       	autenticador.guardarCredenciales(request, usuario);
    }     
}
