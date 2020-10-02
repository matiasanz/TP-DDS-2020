package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import Compra.Item;
import Proveedor.Proveedor;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Factory.ComprasFactory;
import Factory.PresupuestosFactory;
import Presupuesto.Presupuesto;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

public class TestPresupuestoPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void unPresupuestoSePersisteCorrectamente(){
		Presupuesto presupuestoInsertado = PresupuestosFactory.presupuestoPara(ComprasFactory.getCompra12Julio2020SinEtiqueta());
		assertNull(presupuestoInsertado.getId());

		entityManager().persist(presupuestoInsertado);
	    assertNotNull(presupuestoInsertado.getId());
		
	    Presupuesto presupuestoRecuperado = entityManager().find(Presupuesto.class, presupuestoInsertado.getId());
	
	    assertEquals(presupuestoInsertado.getId(), presupuestoInsertado.getId());
	    assertSame(presupuestoRecuperado, presupuestoInsertado);
	}
	
	@Test
	public void PresupuestoSePersisteCorrectamente(){
		Presupuesto presupuestoInsertado = PresupuestosFactory.presupuestoPara(ComprasFactory.getCompra12Julio2020SinEtiqueta());
		assertNull(presupuestoInsertado.getId());
		
		EntityManager entityManager = entityManager();
		
		entityManager.persist(presupuestoInsertado);
		
		entityManager.getTransaction().commit();

	    assertNotNull(presupuestoInsertado.getId());
		
	    Presupuesto presupuestoRecuperado = entityManager().find(Presupuesto.class, presupuestoInsertado.getId());
	
	    assertEquals(presupuestoInsertado.getId(), presupuestoInsertado.getId());
	    assertSame(presupuestoRecuperado, presupuestoInsertado);
	}
}