package Usuario;

public class ContraseniaCoincideConNombreException extends RuntimeException {

    public ContraseniaCoincideConNombreException() {
        super("La contraseña ingresada no debe coincidir con el nombre de usuario");
    }
}