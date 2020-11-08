package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import Direccion.Direccion;
import Direccion.Pais;
import MedioDePago.PagoEnEfectivo;
import Moneda.CodigoMoneda;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMock;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMock;
import Usuario.Usuario;
import Compra.Compra;
import Compra.Item;
import Factory.ComprasFactory;

import org.junit.After;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestCompraPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	@After
	public void end(){
		rollbackTransaction();
	}
	
	@Test
	public void persistenciaCompra(){
		Compra compraInsertada = ComprasFactory.getCompra19Julio2020Amoblamiento();
		assertNull(compraInsertada.getId());
		
		entityManager().persist(compraInsertada);
		
	    assertNotNull(compraInsertada.getId());
		
	    Compra compraRecuperado = entityManager().find(Compra.class, compraInsertada.getId());
	
	    assertEquals(compraInsertada.getId(), compraInsertada.getId());
	    assertSame(compraRecuperado, compraInsertada);
	}
	
	@Test
	public void persistenciaComprasYComitteo(){
		Proveedor unProveedor = Proveedor.PersonaJuridica("Una razón social", new Direccion(new RepositorioDeLocacionesMock(), "Una calle", 2, 2, "1874", Pais.AR));

		Compra compraInsertada = new Compra(new RepositorioDeMonedasMock(),
				null,
				LocalDate.of(2020, 7, 19),
				new PagoEnEfectivo(),
				CodigoMoneda.ARS,
				1);

		Item item = new Item("Una descripcion", 2, new BigDecimal(200));
		compraInsertada.agregarItem(item);

		assertNull(compraInsertada.getId());

		entityManager().persist(unProveedor);
		entityManager().persist(item);
		entityManager().persist(compraInsertada);

//		entityManager().getTransaction().commit(); //Problema: genera efecto de lado
		
	    assertNotNull(compraInsertada.getId());
		
	    Compra compraRecuperado = entityManager().find(Compra.class, compraInsertada.getId());
	
	    assertEquals(compraInsertada.getId(), compraInsertada.getId());
	    assertSame(compraRecuperado, compraInsertada);
	}
}