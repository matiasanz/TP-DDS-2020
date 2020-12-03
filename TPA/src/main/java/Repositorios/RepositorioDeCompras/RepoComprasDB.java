package Repositorios.RepositorioDeCompras;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import Compra.Compra;
import Compra.Estado;
import Repositorios.RepoDB;
import Repositorios.RepositorioDeCompras.RepositorioDeComprasMemoria;

public class RepoComprasDB extends RepoDB<Compra> {

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
}
