package Repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import Compra.Compra;
import Compra.Estado;

public class RepoComprasDB extends RepoDB<Compra>{

	@Override
	protected String className(){
		return "Compra";
	}

    @SuppressWarnings("unchecked")
	public List<Compra> getComprasPendientesDeAprobacion() {
        return createQuery("where indicadorDeAprobacion = :estado")
        		.setParameter("estado", Estado.PENDIENTEDEAPROBACION)
        		.getResultList();
    }

    public RepositorioDeCompras repositorioDelMes(LocalDate fecha){
    	return new RepositorioDeCompras(comprasDelMes(fecha));
    }

    @SuppressWarnings("unchecked")
	public List<Compra> comprasDelMes(LocalDate fechaInicio) {
       return this.getAll()
                .stream()
                .filter(compra -> compra.compraDelMes(fechaInicio))
                .collect(Collectors.toList());
    }
}
