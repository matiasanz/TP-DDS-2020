package Controladores;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class HomeController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	private final String ARCHIVO_LOGIN = "login.html.hbs";
	
    public ModelAndView getHome(Request request, Response response) {
        return getHome(request, response, "");
    }
    
    public ModelAndView getHome(Request request, Response response, String mensaje) {
    	
    	if(usuarioYaAutenticado(request)){
    		response.redirect("/menu");
    	}
    	        					
        return new ModelAndView( generarModelo(mensaje) , ARCHIVO_LOGIN);
    }
    
    private Map<String, Object> generarModelo(String mensaje){
        Map<String, Object> modelo = new HashMap<>();
		modelo.put("mensaje", mensaje);

		return modelo;
    }
    
    private boolean usuarioYaAutenticado(Request request){
    	return request.cookie("uid") != null;
    }
    
}
