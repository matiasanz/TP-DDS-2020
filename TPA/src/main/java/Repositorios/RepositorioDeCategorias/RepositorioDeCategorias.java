package Repositorios.RepositorioDeCategorias;

import Categoria.Categoria;

import java.util.List;

public class RepositorioDeCategorias {
    List<Categoria> categorias;

    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
    }

    public void removerCategoria(Categoria categoria){
        categorias.remove(categoria);
    }
}
