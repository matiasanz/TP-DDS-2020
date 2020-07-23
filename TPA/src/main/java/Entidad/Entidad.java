package Entidad;

import Categoria.Categoria;
import Compra.Compra;
import Repositorios.RepositorioDeCompras.RepositorioDeCompras;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Entidad {
    private final RepositorioDeCompras compras;
    private final List<Categoria> categorias;

    public Entidad() {
        this.compras = new RepositorioDeCompras();
        this.categorias = new ArrayList<>();
    }

    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
    }

    public void agregarCompra(Compra compra) {
        getCategorias().forEach(categoria -> categoria.notificarCompraAgregada(compra.getValorTotal(), this.getValorTodasLasCompras()));
        compras.nuevaCompra(compra);
    }

    public BigDecimal getValorTodasLasCompras() {
        return compras.getCompras().stream().map(Compra::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Compra> getCompras() {
        return compras.getCompras();
    }
    
    public List<Compra> getComprasPendientesDeValidacion() {
    	return compras.getComprasPendientesDeAprobacion();
    }

    private List<Compra> comprasDelMes(LocalDate fechaInicio) {
        return compras
        		.getCompras()
                .stream()
                .filter(compra -> compra.compraDelMes(fechaInicio))
                .collect(Collectors.toList());
    }

    public Map<Integer, Double> obtenerGastosRealizados(LocalDate fechaInicio) {

          return comprasDelMes(fechaInicio)
                  .stream()
                  .collect(Collectors.groupingBy(compra -> compra.getEtiqueta().getIdentificador(),
                    Collectors.summingDouble(Compra -> Compra.getValorTotal().floatValue())));
    }
}
