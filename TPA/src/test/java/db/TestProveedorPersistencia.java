package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Factory.DireccionesFactory;
import Proveedor.Proveedor;

public class TestProveedorPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void personaFisicaSePersisteCorrectamente(){
		Proveedor proveedorInsertado = Proveedor.PersonaFisica(1,3,"Pocho","Saldivar", DireccionesFactory.direccionStub());
		assertNull(proveedorInsertado.getId());
		
		entityManager().persist(proveedorInsertado);
		
	    assertNotNull(proveedorInsertado.getId());
		
	    Proveedor proveedorRecuperado = entityManager().find(Proveedor.class, proveedorInsertado.getId());
	
	    assertEquals(proveedorInsertado.getId(), proveedorInsertado.getId());
	    assertSame(proveedorRecuperado, proveedorInsertado);
	}
}