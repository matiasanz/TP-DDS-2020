package Repositorios.RepositorioDeEtiquetas;

public class EtiquetaConIdentificadorDuplicadoException extends RuntimeException {
    public EtiquetaConIdentificadorDuplicadoException(int identificador) {
        super("Ya existe una etiqueta con identificador = " + identificador);
    }
}