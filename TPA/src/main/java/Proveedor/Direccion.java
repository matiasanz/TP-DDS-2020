package Proveedor;

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
        this.provincia = getProvinciaDeRepositorio();
        this.ciudad = getCiudadDeRepositorio();
    }

    public String getProvinciaDeRepositorio(){
        //TODO falta repositorio
        //return repositorioDeLocaciones.getLocacion().getProvincia();
        return null;
    }

    public String getCiudadDeRepositorio(){
        //TODO falta repositorio
        //return repositorioDeLocaciones.getLocacion().getCiudad();
        return null;
    }


}
