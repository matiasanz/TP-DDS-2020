package Controladores;

import Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MenuController
{
	private final String ARCHIVO_INDEX = "index.html.hbs";
	Autenticador autenticador = Autenticador.getInstance();
	
    public ModelAndView getUserMenu(Request request, Response response){
    	Usuario modelo = autenticador.reconocerUsuario(request, response);
		return new ModelAndView(modelo, ARCHIVO_INDEX);
	}

    public ModelAndView logout(Request request, Response response){
    	autenticador.quitarCredenciales(request, response);
    	response.redirect("/");
    	return null;
    }
    
}
