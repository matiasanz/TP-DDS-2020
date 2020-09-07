package Repositorios;

import Entidad.*;
import Repositorios.RepositorioDeCompras;
import Fabrica.Fabrica;
import Factory.ComprasFactory;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeEntidades {
    private List<Entidad> entidades = new ArrayList<>();

    public List<Entidad> getEntidades() {
        return entidades;
    }

    public void agregarEntidad(Entidad entidad) {
        entidades.add(entidad);
    }
}
