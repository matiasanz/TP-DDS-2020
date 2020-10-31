package Compra;

public class ValorNegativoEnItemDeCompraException extends RuntimeException {
    public ValorNegativoEnItemDeCompraException(){
        super("El valor de la compra no debe ser negativo");
    }
}
