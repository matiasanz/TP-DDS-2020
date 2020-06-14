package Repositorios.RepositorioDeMonedas;

import Repositorios.CodigoMoneda;

public class Moneda {
    private CodigoMoneda codigoMoneda;
    private String descripcion;

    public Moneda(CodigoMoneda codigoMoneda, String descripcion) {
        this.codigoMoneda = codigoMoneda;
        this.descripcion = descripcion;
    }

    public CodigoMoneda getCodigoMoneda() {
        return codigoMoneda;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
