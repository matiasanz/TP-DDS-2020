package Repositorios.RepositorioDeUsuarios;

public class UsuarioDesconocidoException extends RuntimeException
{
	public UsuarioDesconocidoException(String username){
		super("El usuario " + username + "no se encuentra registrado");
	}
}
