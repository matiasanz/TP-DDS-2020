package Mocks;

import Etiqueta.Etiqueta;
import Repositorios.RepositorioDeEtiquetas.RepositorioEtiquetas;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeEtiquetasMock implements RepositorioEtiquetas {

    @Override
    public Etiqueta getEtiquetaDefecto() {
        return null;
    }

    @Override
    public List<Etiqueta> getEtiquetas() {
        return new ArrayList<>();
    }
}
