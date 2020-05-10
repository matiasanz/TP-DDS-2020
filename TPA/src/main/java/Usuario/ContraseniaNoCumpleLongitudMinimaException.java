package Usuario;

public class ContraseniaNoCumpleLongitudMinimaException extends RuntimeException {

    public ContraseniaNoCumpleLongitudMinimaException(Integer cantidadMinimaDeCaracteres) {
        super("La contrase�a ingresada no cumple con la longitud m�nima de "
                + cantidadMinimaDeCaracteres + "caracteres");
    }
}
