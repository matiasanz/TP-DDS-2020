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
        return new ModelAndView(modelo, "index.html.hbs");
    }

	public ModelAndView getUserMenu(Request request, Response response){
		Usuario modelo = new UserController().reconocerUsuario(request); //Dudoso, lo puse aca en realidad para dejarle al otro solo lo relativo a la autenticacion
		return new ModelAndView(modelo, "menu_usuario.html.hbs");
	}
    
}
