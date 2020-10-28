package Controladores;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import Repositorios.RepositorioDeUsuarios.UsuarioNoExisteException;
import Usuario.ErrorDeAutenticacionException;
import Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UserController
{
	private RepoUsuariosDB repoUsuarios = new RepoUsuariosDB();
	    
    public ModelAndView login(Request pedido, Response respuesta) {
	    try{    
	    	iniciarSesion(pedido,respuesta);
	    	respuesta.redirect("/menu");	
	    }
			
	    catch(UsuarioNoExisteException | ErrorDeAutenticacionException __) {
			respuesta.redirect("/", 400); //Agregar mensaje de error
		}

	    return null;
    }

        
    public void iniciarSesion(Request request, Response response){
    	//Forma provisoria de leer los atributos del body. Ver alguna manera de en el html pasar a JSON
       	String nombreUsuario = toModel(request).get("username"); 
       	String contrasenia = toModel(request).get("password");
        	
       	Usuario usuario = repoUsuarios.getByUsername(nombreUsuario);
       	usuario.autenticar(contrasenia);    	
       	
       	response.cookie("uid",   (usuario.getId().toString())   ); //Agregar operacion para no pasarle el id del usuario, agregarle algun logaritmo, algo biyectivo.
    }
    
  //Metodo pensado para una vez que el usuario se autentico, reconocerlo por un id, que quedaria guardado en una cookie
    public Usuario reconocerUsuario(Request pedido){ 
		Long uid = Long.parseLong(pedido.cookie("uid"));
		return repoUsuarios.getByID(uid);
    }
    
    //Auxiliar
    
    private Map<String, String> toModel(Request request){
    	Map<String, String> modelo = new HashMap<>();
    	
    	String[] body  =request.body().split("&"); 
    	Arrays.asList(body).stream().forEach(par->{
    		String[] keyAndValue = par.split("=");
    		modelo.put(keyAndValue[0], keyAndValue[1]);
    	});
    	
    	return modelo;
    }
    //    public Usuario traducir(Request req){      
//        // Convert the JSON string to a POJO obj
//        Gson gson = new GsonBuilder().create();
//        return gson.fromJson(req.body(), Usuario.class);
//    }
}
