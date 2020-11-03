package Controladores;

import java.util.HashMap;
import java.util.Map;

import Exceptions.NingunaSesionAbiertaException;
import Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BandejaController
{
	private final String ARCHIVO_MENSAJES = "mensajes.html.hbs";
	Autenticador autenticador = new Autenticador();
	
	public ModelAndView getBandejaDeMensajes(Request request, Response response){
		try{
			Usuario usuario = autenticador.reconocerUsuario(request);			
			return new ModelAndView(crearModelo(usuario) , ARCHIVO_MENSAJES);		
		} 
		
		catch(NingunaSesionAbiertaException e){
			response.redirect("/");
			return null;
		}
	}
	
	private Map<String, Object> crearModelo(Usuario usuarioAutenticado){
		Map<String, Object> modelo = new HashMap<>();
		modelo.put("tamanioBandeja", usuarioAutenticado.getBandejaDeMensajes().size());
		modelo.put("mensajes", usuarioAutenticado.getBandejaDeMensajes());
		return modelo;
	}
	
	

}
