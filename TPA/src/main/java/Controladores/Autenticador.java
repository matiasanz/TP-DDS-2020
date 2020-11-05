package Controladores;

import Exceptions.NingunaSesionAbiertaException;
import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import Usuario.Usuario;
import spark.Request;
import spark.Response;

public class Autenticador
{
	private RepoUsuariosDB repoUsuarios = new RepoUsuariosDB();
	String USER_ID = "uid"; //Cookie que "persiste" al usuario
	
	public void guardarCredenciales(Request request, Usuario usuario)	{
		Long id = usuario.getId();
		request.session().attribute(USER_ID, id);
	}
	
	public void quitarCredenciales(Request request, Response response){
		request.session().removeAttribute(USER_ID);
    }
	
	//Metodo pensado para una vez que el usuario se autentico, reconocerlo por un id, que quedaria guardado en una cookie
    public Usuario reconocerUsuario(Request pedido){ 
    	validarSesionEnCurso(pedido);
    	Long uid = pedido.session().attribute(USER_ID);
		return repoUsuarios.getByID( uid );
    }

    private void validarSesionEnCurso(Request pedido){
    	if(!sesionEnCurso(pedido)) throw new NingunaSesionAbiertaException();
    }
    
    public boolean sesionEnCurso(Request request){
    	return request.session().attribute(USER_ID) != null;
    }
}
