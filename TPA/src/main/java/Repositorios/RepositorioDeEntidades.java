package Repositorios;

import Entidad.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeEntidades implements WithGlobalEntityManager {

    public static RepositorioDeEntidades instancia = new RepositorioDeEntidades();

    public Entidad findById(Long id) {
        return entityManager().find(Entidad.class, id);
    }

    public List<Entidad> getEntidades(){
        return entityManager().createQuery("from Entidad e", Entidad.class).getResultList();
    }

    public List<Entidad> listarPorCategoria(long categoriaId) {
        return entityManager()//
                .createQuery("select e from Entidad e" +
                        " JOIN e.categorias c" +
                        " WHERE c.id = " + categoriaId, Entidad.class) //
                .getResultList();
    }

    public List<Entidad> getEntidadesByName(String entidadBuscada) {
        return entityManager() //
                .createQuery("from Entidad e where e.nombreFicticio like :nombre", Entidad.class) //
                .setParameter("nombre", "%" + entidadBuscada + "%") //
                .getResultList();
    }

    private List<Entidad> entidades = new ArrayList<>();

    public List<Entidad> getAll() {
        return entidades;
    }
    public void agregar(Entidad entidad) {
        entidades.add(entidad);
    }

    @Transactional
    public void save(Entidad entidad) {
        entityManager().persist(entidad);
    }
}
