package Repositorios.RepositorioDeLocaciones;

import Direccion.Pais;

public class LocacionNoEncontradaException extends RuntimeException {
    public LocacionNoEncontradaException(Pais codigoPais) {
        super("No existe locación correpondiente al Pais" + codigoPais.toString());
    }
}
