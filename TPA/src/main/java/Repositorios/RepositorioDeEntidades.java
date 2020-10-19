package Repositorios;

import Entidad.*;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeEntidades {
	
    private List<Entidad> entidades = new ArrayList<>();

    public List<Entidad> getAll() {
        return entidades;
    }
    public void agregar(Entidad entidad) {
        entidades.add(entidad);
    }
}
