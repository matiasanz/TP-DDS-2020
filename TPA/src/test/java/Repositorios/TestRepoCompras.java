package Repositorios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Compra.Compra;
import Compra.Item;
import Direccion.Direccion;
import Direccion.Pais;
import Factory.ComprasFactory;
import MedioDePago.PagoEnEfectivo;
import Moneda.CodigoMoneda;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMock;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMock;
import Usuario.Usuario;
import junit.framework.Assert;


public class TestRepoCompras extends AbstractPersistenceTest implements WithGlobalEntityManager {

	RepoComprasDB compras = new RepoComprasDB();
	Compra compra = compraPersistible();
	LocalDate fecha = LocalDate.of(2020, 7, 19);
	String etiqueta = "Baratijas";
	
	@Before
	public void start(){
		compras.agregar(compra);
	}
	
	@After
	public void end(){
		this.rollbackTransaction();
	}
	
	@Test
	public void compraSeRecuperaDeDB(){
		List<Compra> comprasRecuperadas = compras.getAll();
		
		Assert.assertEquals(1, comprasRecuperadas.size());		
		Compra recuperada = comprasRecuperadas.get(0);

		AssertCompra(compra,recuperada);
	}
//	
//	@Test
//	public void comprasDelMes(){
//		compras.agregar(ComprasFactory.getCompraFebrero2017SinEtiqueta());
//		List<Compra> comprasDelMes = compras.comprasDelMes(fecha);
//		assertEquals(1, comprasDelMes.size());
//	}
//	
//	@Test
//	public void etiquetasDelMes(){
//		List<String> etiquetas = compras.repositorioDelMes(fecha).getEtiquetas();
//		assertEquals(1, etiquetas.size());
//		Assert.assertEquals(etiqueta, etiquetas.get(0));
//	}
	
	//Auxiliares *****************************

	private void AssertCompra(Compra expected, Compra actual){
		assertEquals(expected.getItems().size(), actual.getItems().size());
		Assert.assertEquals(expected.getValorTotal(),actual.getValorTotal());
		assertEquals(expected.getEtiquetas().size(), actual.getEtiquetas().size());
		assertEquals(expected.getPresupuestosAsociados().size(),actual.getPresupuestosAsociados().size());
	}
	
	private void assertEquals(int expected, int actual){
		Assert.assertEquals(expected, actual);
	}
	
	private Compra compraPersistible(){
		Proveedor unProveedor = Proveedor.PersonaJuridica("Una razón social", new Direccion(new RepositorioDeLocacionesMock(), "Una calle", 2, 2, "1874", Pais.AR));

		Compra compraInsertada = new Compra(new RepositorioDeMonedasMock(),
				null,
				unProveedor,
				fecha,
				new PagoEnEfectivo(),
				CodigoMoneda.ARS,
				1,
				new ArrayList<Usuario>());
		
		Item item = new Item("Una descripcion", 2, new BigDecimal(200));
		compraInsertada.agregarItem(item);
		compraInsertada.agregarEtiqueta(etiqueta);
		return compraInsertada;
	}
}
