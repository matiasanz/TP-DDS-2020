package Repositorios.RepositorioDeLocaciones;

import Proveedor.Pais;

public class Locacion {
    private final Pais codigoPais;
    private final String ciudad;
    private final String provincia;

    public Locacion(Pais codigoPais, String provincia, String ciudad) {
        this.codigoPais = codigoPais;
        this.ciudad = ciudad;
        this.provincia = provincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public Pais getCodigoPais() {
        return codigoPais;
    }

    public String getCiudad() {
        return ciudad;
    }
}
