package Repositorios.RepositorioDeLocaciones;

import Direccion.Pais;

public class LocacionNoEncontradaException extends RuntimeException {
    public LocacionNoEncontradaException(Pais codigoPais, String codigoPostal) {
        super("No existe locación correpondiente al Pais " + codigoPais.toString() + " y código postal " + codigoPostal);
    }
}
