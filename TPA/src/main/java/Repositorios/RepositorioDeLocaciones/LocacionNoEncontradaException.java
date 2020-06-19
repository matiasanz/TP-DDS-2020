package Repositorios.RepositorioDeLocaciones;

import Proveedor.Pais;

public class LocacionNoEncontradaException extends RuntimeException {
    public LocacionNoEncontradaException(Pais codigoPais) {
        super("No existe locación correpondiente al Pais" + codigoPais.toString());
    }
}
