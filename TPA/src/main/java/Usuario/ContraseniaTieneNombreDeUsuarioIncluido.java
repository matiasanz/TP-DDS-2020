package Usuario;

public class ContraseniaTieneNombreDeUsuarioIncluido extends RuntimeException {

    public ContraseniaTieneNombreDeUsuarioIncluido() {
    	super("La contraseņa ingresada no debe incluir el nombre de usuario");
    }
}