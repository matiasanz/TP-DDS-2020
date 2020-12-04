package Exceptions;

public class UsuarioNoExisteException extends RuntimeException
{
	public UsuarioNoExisteException(){}
	
	public UsuarioNoExisteException(String nombre){
		super("El usuario " + nombre + " no se encuentra registrado");
	}
	
	public UsuarioNoExisteException(Long id){
		super("El id " + id.toString() + " no corresponde a ningun usuario");
	}
}
