package Usuario;

public class ErrorDeAutenticacionException extends RuntimeException{
	public ErrorDeAutenticacionException(){
		super("La contrase�a es incorrecta");
	}
}
