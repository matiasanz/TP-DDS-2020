package Controladores;

import spark.ModelAndView;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class HomeController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	private final String ARCHIVO_LOGIN = "login.html.hbs";
	
    public ModelAndView getHome(Response response) {
        return getHome(response, 200, "");
    }
    
    public ModelAndView getHome(Response response, int httpResponse, String mensaje) {
    	response.status(httpResponse);
        Map<String, Object> modelo = new HashMap<>();
        					modelo.put("mensaje", mensaje);
        					
        return new ModelAndView(modelo, ARCHIVO_LOGIN);
    }
    
}
