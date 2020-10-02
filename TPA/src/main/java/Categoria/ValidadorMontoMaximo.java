package Categoria;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CMM")
public class ValidadorMontoMaximo extends Validador {

    @Column(name = "monto_maximo")
    private BigDecimal montoMaximo;
    
    public ValidadorMontoMaximo() {
    	
    }

    public ValidadorMontoMaximo(BigDecimal montoMaximo){
        this.montoMaximo = montoMaximo;
    }

    @Override
    void validarGasto(BigDecimal montoCompra, BigDecimal montoAcumuladoEntidad){
        if(montoCompra.add(montoAcumuladoEntidad).compareTo(montoMaximo) == 1){
            throw new MontoMaximoExcedidoException();
        }
    }

	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}
}