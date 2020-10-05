package Repositorios;

import Compra.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDeCompras {

    public List<Compra> compras;

    public RepositorioDeCompras(List<Compra> compras) {
        this.compras = compras;
    }
    
    public List<Compra> getCompras() {
    	return compras;
    }

    public List<Compra> getComprasPendientesDeAprobacion() {
        return this.getCompras() //<-- con presupuesto elegido (?) 
                .stream()
                .filter(compra -> compra.pendienteDeAprobacion())
                .collect(Collectors.toList());
    }
    
    public void agregarCompra(Compra compra) {
    	compras.add(compra);
    }
    
    public RepositorioDeCompras repositorioDelMes(LocalDate fecha){
    	return new RepositorioDeCompras(comprasDelMes(fecha));
    }
    
    public List<Compra> comprasDelMes(LocalDate fechaInicio) {
        return compras
                .stream()
                .filter(compra -> compra.compraDelMes(fechaInicio))
                .collect(Collectors.toList());
    }
    
    public List<Compra> comprasSinEtiquetar(){
    	return compras.stream().filter(compra -> !compra.etiquetada()).collect(Collectors.toList());
    }

    public List<Compra> comprasConEtiqueta(String etiqueta){
  		 return compras.stream().filter(c -> c.contieneEtiqueta(etiqueta)).collect(Collectors.toList());
    }
    
	public List<String> getEtiquetas(){
		return compras.stream().map(c -> c.getEtiquetas()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
	}
}
