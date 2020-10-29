package Controladores;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Usuario.Usuario;


public class HomeController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
	
    public ModelAndView getHome(Response response) {
        return getHome(response, 200, "");
    }
    
    public ModelAndView getHome(Response response, int httpResponse, String mensaje) {
        Map<String, Object> modelo = new HashMap<>();
        					modelo.put("mensaje", mensaje);
        					
        response.status(httpResponse);
        return new ModelAndView(modelo, "login.hbs");
    }

	public ModelAndView getUserMenu(Request request, Response response){
		Usuario modelo = new UserController().reconocerUsuario(request);
		return new ModelAndView(modelo, "index.html.hbs");
	}
	
	public ModelAndView getBandejaDeMensajes(Request request, Response response){
		Usuario usuarioAutenticado = new UserController().reconocerUsuario(request);
		Map<String, Object> modelo = new HashMap<>();
		modelo.put("tamanioBandeja", usuarioAutenticado.getBandejaDeMensajes().size());
		
		return new ModelAndView(modelo, "mensajes.html.hbs");		
	}
    
}
