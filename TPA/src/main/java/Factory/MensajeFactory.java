package Factory;

import Compra.Compra;
import Usuario.Mensaje;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MensajeFactory {

    private static String mensajeValidacionCompraHeader(Compra compra) {
        return "La compra fechada el día " + compra.getFechaOperacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " por un total de $ " + compra.getValorTotal() +" perteneciente a la entidad "
                + compra.getEntidadRelacionada().getNombreFicticio();
    }

    public static Mensaje mensajeDeAprobacion(Compra compra) {

        String mensaje = mensajeValidacionCompraHeader(compra) + " ha sido aprobada";
        return new Mensaje(LocalDateTime.now(), mensaje, 0);
    }

    public static Mensaje mensajeDeRechazo(Compra compra, String motivoRechazo) {

        String mensaje = mensajeValidacionCompraHeader(compra) + " ha sido sido rechazada, motivo: " + motivoRechazo;
        return new Mensaje(LocalDateTime.now(), mensaje, 0);
    }
}
