package Categoria;

import java.math.BigDecimal;

public class ValidadorMontoMaximo extends Validador {
    private BigDecimal montoMaximo;

    public ValidadorMontoMaximo(BigDecimal montoMaximo){
        this.montoMaximo = montoMaximo;
    }

    @Override
    void validarGasto(BigDecimal montoCompra, BigDecimal montoAcumuladoEntidad){
        if(montoCompra.add(montoAcumuladoEntidad).compareTo(montoMaximo) == 1){
            throw new MontoMaximoExcedidoException();
        }
    }
}