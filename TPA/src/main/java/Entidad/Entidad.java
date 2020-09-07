package Entidad;

import Categoria.Categoria;
import Compra.Compra;
import Repositorios.RepositorioDeCompras;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public abstract class Entidad {
    private final RepositorioDeCompras compras;
    private final List<Categoria> categorias;

    public Entidad() {
    							//No usa repo meli
        this.compras = new RepositorioDeCompras(new ArrayList<>());
        this.categorias = new ArrayList<>();
    }
    
// Categoria ****************

    public List<Categoria> getCategorias() {
        return categorias;
    }
    
    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
    }

// Compras ****************
    public List<Compra> getCompras() {
    	return compras.getCompras();
    }

    public void agregarCompra(Compra compra) {
        getCategorias().forEach(categoria -> categoria.notificarCompraAgregada(compra.getValorTotal(), this.getValorTodasLasCompras()));
        compras.agregarCompra(compra);
    }

    public BigDecimal getValorTodasLasCompras() {
        return compras.getCompras().stream().map(Compra::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Compra> getComprasPendientesDeValidacion() {
    	return compras.getComprasPendientesDeAprobacion();
    }
  
    public Map<String, Double> obtenerGastosRealizados(LocalDate fechaInicio) {
        
    	RepositorioDeCompras comprasDelMes = compras.repositorioDelMes(fechaInicio);
    	List<String> etiquetasDelMes = comprasDelMes.getEtiquetas();

        Map<String, Double> gastosRealizados = new HashMap<>();

        for(String etiqueta : etiquetasDelMes){
        	Double valor = this._calcularValorCompras(comprasDelMes.comprasConEtiqueta(etiqueta));        	
            gastosRealizados.put(etiqueta, valor);
        }
         
        List<Compra> sinEtiquetar = comprasDelMes.comprasSinEtiquetar();
        if(!sinEtiquetar.isEmpty()){        	
        	gastosRealizados.put("Sin Etiquetar", this._calcularValorCompras(sinEtiquetar));
        }
        
        return gastosRealizados;
    }
        
    private double _calcularValorCompras(List<Compra> unasCompras){
    	return unasCompras.stream().map(c -> c.getValorTotal())
    			.reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
    }
}
