package Factory;

import MedioDePago.*;

public class MedioDePagoFactory {

    public static MedioDePago effectivo() {
        return new PagoEnEfectivo();
    }

    public static MedioDePago tarjetaDeCreditoVisa() {
        return new TarjetaDeCredito("Dario Steffanato", "123445643466", "VISA");
    }

    public static MedioDePago tarjetaDeDebito() {
        return new TarjetaDeDebito("Pepe Romero", "475847465757");
    }
}
