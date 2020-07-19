package Entidad;

import Categoria.Categoria;
import Compra.Compra;

import java.math.BigDecimal;
import java.util.List;

public abstract class Entidad {
    private List<Compra> compras;
    private List<Categoria> categorias;

    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
    }

    public void agregarCompra(Compra compra) {
        getCategorias().forEach(categoria -> categoria.notificarCompraAgregada(compra.getEntidadRelacionada()));
        compras.add(compra);
    }

    public BigDecimal getValorTodasLasCompras() {
        return compras.stream().map(Compra::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Compra> getCompras() {
        return compras;
    }
}
