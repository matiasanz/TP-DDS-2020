package Compra;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String descripcion;

    private int cantidad;

    @Column(name = "valor_unitario")

    private BigDecimal valorUnitario;

    public BigDecimal getValor() {
        return valorUnitario.multiply(new BigDecimal(cantidad));
    }

    public BigDecimal getValorUnitario(){
        return valorUnitario;
    }
    
    public Long getId(){
		return id;
	}

    //Constructor
    public Item(String descripcion, int cantidad, BigDecimal valorUnitario) {
    	
    	validarValorPositivo(valorUnitario);
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
    }

	private void validarValorPositivo(BigDecimal valor) {
		if (valor.intValue() < 0) {
    		throw new ValorNegativoEnItemDeCompraException();
    	}
	}
	
	public String toString(){
		return String.join(" ",descripcion, valorUnitario.toString(), Integer.toString(cantidad));
	}

}
