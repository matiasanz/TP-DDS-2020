package Usuario;

public class ContraseniaTieneNombreDeUsuarioIncluido extends RuntimeException {

    public ContraseniaTieneNombreDeUsuarioIncluido() {
    	super("La contraseña ingresada no debe incluir el nombre de usuario");
    }
}