package Usuario;

public class ContraseniaTieneNombreDeUsuarioIncluido extends RuntimeException {

    public ContraseniaTieneNombreDeUsuarioIncluido() {
    	super("La contrase�a ingresada no debe incluir el nombre de usuario");
    }
}