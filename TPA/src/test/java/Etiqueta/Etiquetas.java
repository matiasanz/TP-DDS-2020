package Etiqueta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Compra.Estado;
import Direccion.Direccion;
import Direccion.Pais;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeEtiquetas.RepositorioEtiquetas;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;


public class Etiquetas {
	private Direccion direccion;
	private Proveedor proveedor1;
	private Proveedor proveedor2;	
	private RepositorioEtiquetas repositorioDeEtiquetas;
	
	@Before
    public void init() {
		
		proveedor1= Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccion);
		proveedor2= Proveedor.PersonaFisica(22223222, 1222422224, "Jorge", "Paez", direccion);
		repositorioDeEtiquetas=new RepositorioEtiquetas();
	}
	
	@Test
    public void agregarEtiquetaPersonalizada(){	
		Etiqueta etiqueta1 = new EtiquetaPersonalizable("inmobilaria");
		Etiqueta etiqueta2 = new EtiquetaPersonalizable("indumentaria");
		repositorioDeEtiquetas.agregarEtiqueta(etiqueta1);
		repositorioDeEtiquetas.agregarEtiqueta(etiqueta2);	
		assertEquals(5, repositorioDeEtiquetas.getEtiquetas().size());
		
	}
	
	@Test
	public void agregarEtiquetaProveedor(){	
		Etiqueta etiqueta1 = new EtiquetaProveedor(proveedor1);
		Etiqueta etiqueta2 = new EtiquetaProveedor(proveedor2);
		repositorioDeEtiquetas.agregarEtiqueta(etiqueta1);
		repositorioDeEtiquetas.agregarEtiqueta(etiqueta2);	
		assertEquals(5, repositorioDeEtiquetas.getEtiquetas().size());
		
	}
	

}
