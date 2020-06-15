package Repositorios.RepositorioDeLocaciones;
import Proveedor.Pais;

public interface RepositorioDeLocaciones {
    Locacion getLocacion(Pais codigoPais, String codigoPostal);
}
