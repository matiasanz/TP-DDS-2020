package Controladores;

import java.util.HashMap;
import java.util.Map;
import Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BandejaController {
	public ModelAndView getBandejaDeMensajes(Request request, Response response){
		Usuario usuario = Autenticador.instance.getUsuario(request, response);
		return new ModelAndView(crearModelo(usuario) , "mensajes.html.hbs");
	}
	
	private Map<String, Object> crearModelo(Usuario usuarioAutenticado){
		Map<String, Object> modelo = new HashMap<>();
		modelo.put("tamanioBandeja", usuarioAutenticado.getBandejaDeMensajes().size());
		modelo.put("mensajes", usuarioAutenticado.getBandejaDeMensajes());
		return modelo;
	}
}
