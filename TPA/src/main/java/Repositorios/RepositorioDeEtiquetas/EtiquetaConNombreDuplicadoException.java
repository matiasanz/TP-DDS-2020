package Repositorios.RepositorioDeEtiquetas;

public class EtiquetaConNombreDuplicadoException extends RuntimeException {
    public EtiquetaConNombreDuplicadoException(String nombre) {
        super("Ya existe una etiqueta con nombre = " + nombre);
    }
}