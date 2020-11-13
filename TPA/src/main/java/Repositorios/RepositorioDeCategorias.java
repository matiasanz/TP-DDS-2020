package Repositorios;

import Categoria.Categoria;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioDeCategorias implements WithGlobalEntityManager {

    public static RepositorioDeCategorias instancia = new RepositorioDeCategorias();

    public Categoria findById(Long id) {
        return entityManager().find(Categoria.class, id);
    }

    public List<Categoria> findAll(){
        return entityManager().createQuery("FROM Categoria ORDER BY nombre ASC", Categoria.class).getResultList();
    }

    public List<Categoria> getCategoriasByName(String categoriaBuscada) {
        return entityManager() //
                .createQuery("from Categoria c where c.nombre like :nombre", Categoria.class) //
                .setParameter("nombre", "%" + categoriaBuscada + "%") //
                .getResultList();
    }


    ////

    List<Categoria> categorias;

    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
    }

    public void removerCategoria(Categoria categoria){
        categorias.remove(categoria);
    }
}
