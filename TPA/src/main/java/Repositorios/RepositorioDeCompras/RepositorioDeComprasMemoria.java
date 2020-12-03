package Repositorios.RepositorioDeCompras;

import Compra.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDeComprasMemoria {

    public List<Compra> compras;

    public RepositorioDeComprasMemoria(List<Compra> compras) {
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
    
    public void agregar(Compra compra) {
    	compras.add(compra);
    }
    
    public RepositorioDeComprasMemoria repositorioDelMes(LocalDate fecha){
    	return new RepositorioDeComprasMemoria(comprasDelMes(fecha));
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
