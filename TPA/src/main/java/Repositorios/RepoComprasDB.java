package Repositorios;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import Compra.Compra;
import Compra.Estado;

public class RepoComprasDB extends RepoDB<Compra>{

	@Override
	protected String className(){
		return "Compra";
	}

	public Compra getCompra(Long id) {
		return this.getAll().stream().filter(compra -> compra.getId().equals(id)).collect(Collectors.toList()).get(0);
	}
	
    @SuppressWarnings("unchecked")
	public List<Compra> getComprasPendientesDeAprobacion() {
        return query("where indicadorDeAprobacion = :estado")
        		.setParameter("estado", Estado.PENDIENTEDEAPROBACION)
        		.getResultList();
    }

	public List<Compra> comprasDelMes(LocalDate fechaInicio) {
       return this.getAll()
                .stream()
                .filter(compra -> compra.compraDelMes(fechaInicio))
                .collect(Collectors.toList());
    }
        
    public RepositorioDeComprasMemoria repositorioDelMes(LocalDate fecha){
    	return new RepositorioDeComprasMemoria(comprasDelMes(fecha));
    }
   
    public List<Compra> comprasSinEtiquetar(){
    	return getAll().stream().filter(compra -> !compra.etiquetada()).collect(Collectors.toList());
    }

    public List<Compra> comprasConEtiqueta(String etiqueta){
  		 return getAll().stream().filter(c -> c.contieneEtiqueta(etiqueta)).collect(Collectors.toList());
    }
    
	public List<String> getEtiquetas(){
		return getAll().stream().map(c -> c.getEtiquetas()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
	}
}
