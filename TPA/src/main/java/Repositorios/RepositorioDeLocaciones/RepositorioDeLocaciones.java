package Repositorios.RepositorioDeLocaciones;

import Direccion.Pais;

public interface RepositorioDeLocaciones {
    Locacion getLocacion(Pais codigoPais, String codigoPostal);
}
