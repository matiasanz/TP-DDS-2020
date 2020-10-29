package Exceptions;

public class ErrorDeAutenticacionException extends RuntimeException{
	public ErrorDeAutenticacionException(){
		super("La contraseña es incorrecta");
	}
}
