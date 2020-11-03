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

	//Pagina de inicio de sesion
    public ModelAndView getHome(Request request, Response response) {
        return getHome(request, response, "");
    }
    
    public ModelAndView getHome(Request request, Response response, String mensaje) {	        					
        if(autenticador.sesionEnCurso(request)){
        	response.redirect("/menu");
        }

    	return new ModelAndView( generarModelo(mensaje) , ARCHIVO_LOGIN);
    }
    
    private Map<String, Object> generarModelo(String mensaje){
        Map<String, Object> modelo = new HashMap<>();
		modelo.put("mensaje", mensaje);

		return modelo;
    }
	
    public ModelAndView tryLogin(Request pedido, Response respuesta) {
	    try{    
	    	iniciarSesion(pedido,respuesta);
	    	respuesta.status(HttpURLConnection.HTTP_ACCEPTED);
	    	respuesta.redirect("/menu");	
	    	return null;
	    }
			
	    catch(UsuarioNoExisteException | ErrorDeAutenticacionException e) {
	    	respuesta.status(HttpURLConnection.HTTP_PROXY_AUTH);
			return getHome(pedido, respuesta,
					"**El usuario y/o la contraseña ingresada son incorrectos");
		}
    }
    
    public void iniciarSesion(Request request, Response response){
       	Usuario usuario = repoUsuarios.getUsuario(request.queryParams("username"));
       	usuario.autenticar(request.queryParams("password"));
       	autenticador.guardarCredenciales(request, usuario);
    }    
}
