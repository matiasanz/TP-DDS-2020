package MedioDePago;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("E")
public class PagoEnEfectivo extends MedioDePago {

    @Override
    public String getDescripcion() {
        return "Pago en efectivo";
    }
}
