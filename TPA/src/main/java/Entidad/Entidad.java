package Entidad;

import Categoria.Categoria;
import Compra.Compra;
import Categoria.MontoMaximoExcedidoException;
import Etiqueta.Etiqueta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Entidad {
    private List<Compra> compras = new ArrayList<>();
    private List<Categoria> categorias = new LinkedList();

    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public void agregarCompra(Compra compra) {
        try {
            getCategorias().forEach(categoria -> categoria.notificarCompraAgregada(compra.getValorTotal(), this.getValorTodasLasCompras()));
            compras.add(compra);
        } catch (MontoMaximoExcedidoException e) {

        }

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

    private List<Compra> comprasPosterioresA(LocalDate fechaInicio) {
        return compras
                .stream()
                .filter(compra -> compra.conFechaAnteriorOIgualA(fechaInicio))
                .collect(Collectors.toList());
    }

    public Map<Etiqueta, Double> obtenerGastosRealizados(LocalDate fechaInicio) {

          return comprasPosterioresA(fechaInicio).stream().collect(Collectors.groupingBy(Compra::getEtiqueta,
                Collectors.summingDouble(Compra -> Compra.getValorTotal().floatValue())));
    }
}
