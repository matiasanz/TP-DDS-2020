package Usuario;

public class ErrorDeAutenticacionException extends RuntimeException{
	public ErrorDeAutenticacionException(){
		super("La contraseņa es incorrecta");
	}
}
