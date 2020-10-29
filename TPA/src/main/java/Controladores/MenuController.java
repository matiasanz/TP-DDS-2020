package Controladores;

import Exceptions.NingunaSesionAbiertaException;
import Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MenuController
{
	private final String ARCHIVO_INDEX = "index.html.hbs";
	Autenticador autenticador = new Autenticador();
	
    public ModelAndView getUserMenu(Request request, Response response){
    	try{
			Usuario modelo = autenticador.reconocerUsuario(request);
			return new ModelAndView(modelo, ARCHIVO_INDEX);
    	}
    	catch(NingunaSesionAbiertaException e){
    		response.redirect("/");
    		return null;
    	}
	}
}
