package MedioDePago;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("C")
public class CajeroAutomatico extends MedioDePago {
    private String titular;
    private double cbu; //22 Digitos

    public CajeroAutomatico() {
    }

    public void CajeroAutomatico(String titular, double cbu) {
        this.titular = titular;
        this.cbu = cbu;
    }

    @Override
    public String getDescripcion() {
        return "Cajero automatico - " + "CBU: " + this.cbu;
    }
}
