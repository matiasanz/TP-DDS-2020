package Repositorios.RepositorioDeLocaciones;
import Repositorios.RepositorioDeMonedas.CodigoPais;

public interface RepositorioDeLocaciones {
    Locacion getLocacion(CodigoPais codigoPais, String codigoPostal);
}
