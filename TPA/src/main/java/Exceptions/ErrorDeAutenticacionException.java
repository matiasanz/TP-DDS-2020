package Exceptions;

public class ErrorDeAutenticacionException extends RuntimeException{
	public ErrorDeAutenticacionException(){
		super("La contrase�a es incorrecta");
	}
}
