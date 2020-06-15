package Proveedor;

import Repositorios.RepositorioDeLocaciones.Locacion;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocaciones;

public class Direccion {
    private RepositorioDeLocaciones repositorioDeLocaciones;
    String calle;
    int altura;
    int piso;
    String codigoPostal;
    Pais pais;
    String provincia;
    String ciudad;

    public Direccion(RepositorioDeLocaciones repositorioDeLocaciones, String calle,
                     int altura, int piso, String codigoPostal,
                     Pais pais, String provincia, String ciudad) {

        this.repositorioDeLocaciones = repositorioDeLocaciones;
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        setLocacion(Pais.AR, codigoPostal);
    }

    private void setLocacion(Pais codigoPais, String codigoPostal) {

        Locacion locacion = repositorioDeLocaciones.getLocacion(codigoPais, codigoPostal);
        this.provincia = locacion.getProvincia();
        this.ciudad = locacion.getCiudad();
    }


}
