package Usuario;

public class ContraseniaTieneCaracteresRepetidosException extends RuntimeException {

    public ContraseniaTieneCaracteresRepetidosException() {
        super("La contraseña ingresada posee caracteres repetidos");
    }
}