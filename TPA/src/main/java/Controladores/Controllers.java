package Controladores;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;

//Auxiliar
public class Controllers {
 
	//Forma provisoria de leer los atributos del body. Ver alguna manera de en el html pasar a JSON
    public static Map<String, String> getBody(Request request){
    	Map<String, String> modelo = new HashMap<>();
    	
    	String[] body  =request.body().split("&"); 
    	Arrays.asList(body).stream().forEach(par->{
    		String[] keyAndValue = par.split("=");
    		modelo.put(keyAndValue[0], keyAndValue[1]);
    	});
    	
    	return modelo;
    }

//TODO investigar esta forma -> requiere desde el html parsear a JSon
//    	 traducir(Request req){      
//        // Convert the JSON string to a POJO obj
//        Gson gson = new GsonBuilder().create();
//        return gson.fromJson(req.body(), Usuario.class);
//    }
}
