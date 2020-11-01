package Repositorios;

import Entidad.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeEntidades implements WithGlobalEntityManager {

    public static RepositorioDeEntidades instancia = new RepositorioDeEntidades();

    public List<Entidad> listarPorCategoria(long categoriaId) {
        return entityManager()//
                .createQuery("select e from Entidad e" +
                        " JOIN e.categorias c" +
                        " WHERE c.id = " + categoriaId, Entidad.class) //
                .getResultList();
    }

    private List<Entidad> entidades = new ArrayList<>();

    public List<Entidad> getAll() {
        return entidades;
    }
    public void agregar(Entidad entidad) {
        entidades.add(entidad);
    }
}
