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
	
    public ModelAndView getHome() {
        Map<String, Object> modelo = new HashMap<>();
        return new ModelAndView(modelo, "login.hbs");
    }

	public ModelAndView getUserMenu(Request request, Response response){
		Usuario modelo = new UserController().reconocerUsuario(request);
		return new ModelAndView(modelo, "index.html.hbs");
	}
    
}
