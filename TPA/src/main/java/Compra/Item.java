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
    
    public void setId(Long id) {
		this.id = id;
	}
    
    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

    //Constructor
    public Item() {
    	
    }
    
    public Item(String descripcion, int cantidad, BigDecimal valorUnitario) {
    	
    	validarValorPositivo(valorUnitario);
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
    }
    
    public boolean equals(Item item){
    	return descripcion == item.getDescripcion()
    		&& cantidad == item.getCantidad();
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
