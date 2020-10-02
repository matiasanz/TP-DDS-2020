package Direccion;

import Repositorios.RepositorioDeLocaciones.Locacion;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocaciones;

import javax.persistence.*;

@Embeddable
public class Direccion {
    @Transient
    private RepositorioDeLocaciones repositorioDeLocaciones;

    String calle;
    int altura;
    int piso;

    @Column(name = "codigo_postal")
    String codigoPostal;

    @Enumerated(EnumType.STRING)
    Pais pais;

    String provincia;
    String ciudad;

    public Direccion(){} //Constructor principal para orm
    
    public Direccion(RepositorioDeLocaciones repositorioDeLocaciones, String calle,
                     int altura, int piso, String codigoPostal,
                     Pais pais) {

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

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


}