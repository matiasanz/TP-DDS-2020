package Usuario;

public class ContraseniaTieneNombreDeUsuarioIncluidoException extends RuntimeException {

    public ContraseniaTieneNombreDeUsuarioIncluidoException() {
    	super("La contrase�a ingresada no debe incluir el nombre de usuario");
    }
}