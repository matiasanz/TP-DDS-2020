package Entidad;

import Categoria.Categoria;
import Compra.Compra;
import Repositorios.RepositorioDeCompras.RepositorioDeCompras;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Entidad {
    private final RepositorioDeCompras compras;
    private final List<Categoria> categorias;

    public Entidad() {
        this.compras = new RepositorioDeCompras(new RepositorioDeMonedasMeli());
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

    public Map<String, Double> obtenerGastosRealizados(LocalDate fechaInicio) {
        List<String> etiquetasDelMes = comprasDelMes(fechaInicio).stream().map(c -> c.getEtiquetas()).flatMap(Collection::stream).distinct().collect(Collectors.toList());

        Map<String, Double> aRetornar = new HashMap<>();

        for(String etiqueta : etiquetasDelMes){
            aRetornar.put(etiqueta, comprasDelMes(fechaInicio).stream().filter(c -> c.contieneEtiqueta(etiqueta)).map(c -> c.getValorTotal()).reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue());
        }
        return aRetornar;
    }
}
