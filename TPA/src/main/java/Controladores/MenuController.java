package Controladores;

import Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class MenuController
{
	private final String ARCHIVO_INDEX = "index.html.hbs";
	Autenticador autenticador = Autenticador.instance;
	
    public ModelAndView getUserMenu(Request request, Response response){
    	Usuario modelo = autenticador.getUsuario(request, response);
		return new ModelAndView(modelo, ARCHIVO_INDEX);
	}

    public ModelAndView logout(Request request, Response response){
    	autenticador.quitarCredenciales(request, response);
    	response.redirect("/");
    	return new ModelAndView(new HashMap<>(), "");
    }
    
}
