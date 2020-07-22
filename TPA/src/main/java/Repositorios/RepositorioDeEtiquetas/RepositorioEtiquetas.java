package Repositorios.RepositorioDeEtiquetas;
import Etiqueta.Etiqueta;
import java.util.List;

public interface RepositorioEtiquetas {

    Etiqueta getEtiquetaDefecto();
    List<Etiqueta> getEtiquetas();
}
