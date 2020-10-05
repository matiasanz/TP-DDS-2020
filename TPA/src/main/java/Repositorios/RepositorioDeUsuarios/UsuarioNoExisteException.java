package Repositorios.RepositorioDeUsuarios;

public class UsuarioNoExisteException extends RuntimeException
{
	public UsuarioNoExisteException(String nombre){
		super("El usuario " + nombre + " no se encuentra registrado");
	}
}
