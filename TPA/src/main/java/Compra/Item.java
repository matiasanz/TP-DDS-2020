package Compra;

import java.math.BigDecimal;

public class Item {
    private String descripcion;
    private BigDecimal valor;

    public BigDecimal getValor() {
        return valor;
    }

    public Item(String descripcion, BigDecimal valor) {
    	
    	validarValorPositivo(valor);
        this.descripcion = descripcion;
        this.valor = valor;
    }

	private void validarValorPositivo(BigDecimal valor) {
		if (valor.intValue() < 0) {
    		throw new ValorNegativoEnItemDeCompraException();
    	}
	}

}
