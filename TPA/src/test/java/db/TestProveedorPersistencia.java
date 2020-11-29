/*package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Factory.DireccionesFactory;
import Proveedor.Proveedor;

public class TestProveedorPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void personaFisicaSePersisteCorrectamente(){
		Proveedor proveedorInsertado = Proveedor.PersonaFisica(1,3,"Pocho","Saldivar", DireccionesFactory.direccionStub9DeJulio());
		assertNull(proveedorInsertado.getId());
		
		entityManager().persist(proveedorInsertado);
		
	    assertNotNull(proveedorInsertado.getId());
		
	    Proveedor proveedorRecuperado = entityManager().find(Proveedor.class, proveedorInsertado.getId());
	
	    assertEquals(proveedorInsertado.getId(), proveedorInsertado.getId());
	    assertSame(proveedorRecuperado, proveedorInsertado);
	}
	
	@Test
	public void personaFisicaSePersisteCorrectamentes(){
		Proveedor proveedorInsertado = Proveedor.PersonaFisica(1,3,"Pocho","Saldivar", DireccionesFactory.direccionStub9DeJulio());
		assertNull(proveedorInsertado.getId());
		
		EntityManager entityManager = entityManager();
		
		entityManager.persist(proveedorInsertado);
		
		entityManager.getTransaction().commit();
		
	    assertNotNull(proveedorInsertado.getId());
		
	    Proveedor proveedorRecuperado = entityManager().find(Proveedor.class, proveedorInsertado.getId());
	
	    assertEquals(proveedorInsertado.getId(), proveedorInsertado.getId());
	    assertSame(proveedorRecuperado, proveedorInsertado);
	}
	
	@Test
	public void personaJuridicaSePersisteCorrectamentes(){
		Proveedor proveedorInsertado = Proveedor.PersonaJuridica("Hermanos Saldivar", DireccionesFactory.direccionStub9DeJulio());
		assertNull(proveedorInsertado.getId());
		
		EntityManager entityManager = entityManager();
		
		entityManager.persist(proveedorInsertado);
		
		entityManager.getTransaction().commit();
		
	    assertNotNull(proveedorInsertado.getId());
		
	    Proveedor proveedorRecuperado = entityManager().find(Proveedor.class, proveedorInsertado.getId());
	
	    assertEquals(proveedorInsertado.getId(), proveedorInsertado.getId());
	    assertSame(proveedorRecuperado, proveedorInsertado);
	}
} */