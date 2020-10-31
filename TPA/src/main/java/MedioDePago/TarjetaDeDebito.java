package MedioDePago;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tarjetas_de_debito")
@DiscriminatorValue("U")
public class TarjetaDeDebito extends MedioDePago {
    private String titular;
    private String numero; //hay que ver como modelamos una tarjeta

    public TarjetaDeDebito() {
    }

    public TarjetaDeDebito(String titular, String numero) {
        this.titular = titular;
        this.numero = numero;
    }

    @Override
    public String getDescripcion() {
        return "Tarjeta De Debito - Titular: " + titular;
    }
}

