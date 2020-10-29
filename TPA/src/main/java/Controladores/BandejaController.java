package Controladores;

import java.util.HashMap;
import java.util.Map;

import Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BandejaController
{
	private final String ARCHIVO_MENSAJES = "mensajes.html.hbs";
	
	public ModelAndView getBandejaDeMensajes(Request request, Response response){
		Usuario usuario = new MenuController().reconocerUsuario(request);			
		return new ModelAndView( crearModelo(usuario) , ARCHIVO_MENSAJES);		
	}
	
	private Map<String, Object> crearModelo(Usuario usuarioAutenticado){
		Map<String, Object> modelo = new HashMap<>();
		modelo.put("tamanioBandeja", usuarioAutenticado.getBandejaDeMensajes().size());
		
		return modelo;
	}

}
