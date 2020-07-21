package Repositorios.RepositorioDeMonedas;

import Moneda.CodigoMoneda;

public class MonedaNoEncontradaException extends RuntimeException {
    public MonedaNoEncontradaException(CodigoMoneda codigoMoneda) {
        super("No existe moneda con código" + codigoMoneda.toString());
    }
}
