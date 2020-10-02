package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Compra.Compra;
import Factory.ComprasFactory;

public class TestCompraPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
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
	public void persistenciaCompras(){
		Compra compraInsertada = ComprasFactory.getCompra19Julio2020Amoblamiento();
		assertNull(compraInsertada.getId());
		
		EntityManager entityManager = entityManager();
		
		entityManager.persist(compraInsertada);
		entityManager.getTransaction().commit();
		
	    assertNotNull(compraInsertada.getId());
		
	    Compra compraRecuperado = entityManager().find(Compra.class, compraInsertada.getId());
	
	    assertEquals(compraInsertada.getId(), compraInsertada.getId());
	    assertSame(compraRecuperado, compraInsertada);
	}
}