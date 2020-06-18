package Usuario;

public class ContraseniaEntreLasDiezMilException extends RuntimeException {

    public ContraseniaEntreLasDiezMilException() {
        super("La contraseña ingresada no cumple con los requisitos minimos de seguridad");
    }
}
