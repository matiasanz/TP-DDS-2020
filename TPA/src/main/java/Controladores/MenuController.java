package Controladores;

import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MenuController
{
	private final String ARCHIVO_INDEX = "index.html.hbs";
	RepoUsuariosDB repoUsuarios = new RepoUsuariosDB();
	
    public ModelAndView getUserMenu(Request request, Response response){
		Usuario modelo = reconocerUsuario(request);
		return new ModelAndView(modelo, ARCHIVO_INDEX);
	}
    
    //Metodo pensado para una vez que el usuario se autentico, reconocerlo por un id, que quedaria guardado en una cookie
    public Usuario reconocerUsuario(Request pedido){ 
		Long uid = Long.parseLong(pedido.cookie("uid"));
		return repoUsuarios.getByID(uid);
    }

}
