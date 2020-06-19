package Usuario;

public class ContraseniaNoCumpleLongitudMinimaException extends RuntimeException {

    public ContraseniaNoCumpleLongitudMinimaException(Integer cantidadMinimaDeCaracteres) {
        super("La contraseña ingresada no cumple con la longitud mínima de "
                + cantidadMinimaDeCaracteres + "caracteres");
    }
}