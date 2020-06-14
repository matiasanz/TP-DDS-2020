package Repositorios.RepositorioDeLocaciones;

import Repositorios.RepositorioDeMonedas.CodigoPais;

public class Locacion {
    private final CodigoPais codigoPais;
    private final String ciudad;
    private final String provincia;

    public Locacion(CodigoPais codigoPais, String provincia, String ciudad) {
        this.codigoPais = codigoPais;
        this.ciudad = ciudad;
        this.provincia = provincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public CodigoPais getCodigoPais() {
        return codigoPais;
    }

    public String getCiudad() {
        return ciudad;
    }
}
