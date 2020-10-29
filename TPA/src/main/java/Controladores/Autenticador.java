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
	
	public void guardarCredenciales(Response response, Usuario usuario)	{
		Long id = usuario.getId();
		response.cookie(USER_ID, id.toString() );
		//Ver si vale la pena agregar operacion para no pasarle el id del usuario, agregarle algun logaritmo, algo biyectivo.
	}
	
	public void quitarCredenciales(Request request, Response response){
    	request.cookies().remove(USER_ID);
    	response.removeCookie(USER_ID);
    }
	
	//Metodo pensado para una vez que el usuario se autentico, reconocerlo por un id, que quedaria guardado en una cookie
    public Usuario reconocerUsuario(Request pedido){ 
    	validarSesionEnCurso(pedido);
    	String uid = pedido.cookie(USER_ID);
		return repoUsuarios.getByID( Long.parseLong(uid)  );
    }

    private void validarSesionEnCurso(Request pedido){
    	if(!sesionEnCurso(pedido)) throw new NingunaSesionAbiertaException();
    }
    
    public boolean sesionEnCurso(Request request){
    	return request.cookie(USER_ID) != null;
    }
}
