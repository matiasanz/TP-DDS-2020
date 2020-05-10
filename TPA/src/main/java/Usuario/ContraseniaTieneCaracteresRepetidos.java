package Usuario;

public class ContraseniaTieneCaracteresRepetidos extends RuntimeException {

    public ContraseniaTieneCaracteresRepetidos() {
        super("La contraseña ingresada posee caracteres repetidos");
    }
}