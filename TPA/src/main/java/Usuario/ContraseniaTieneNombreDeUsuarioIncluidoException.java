package Usuario;

public class ContraseniaTieneNombreDeUsuarioIncluidoException extends RuntimeException {

    public ContraseniaTieneNombreDeUsuarioIncluidoException() {
    	super("La contraseña ingresada no debe incluir el nombre de usuario");
    }
}