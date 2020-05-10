package Usuario;

public class ContraseniaEntreLasDiezMilException extends RuntimeException {

    public ContraseniaEntreLasDiezMilException() {
        super("La contrase�a ingresada no cumple con los requisitos minimos de seguridad");
    }
}
