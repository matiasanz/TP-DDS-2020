package Repositorios.RepositorioDeMonedas;

import Moneda.CodigoMoneda;
import Proveedor.Pais;

public class MonedaNoEncontradaException extends RuntimeException {
    public MonedaNoEncontradaException(CodigoMoneda codigoMoneda) {
        super("No existe moneda con código" + codigoMoneda.toString());
    }
}
