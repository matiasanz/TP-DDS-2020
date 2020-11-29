/*package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Compra.Item;
import Factory.ItemsFactory;

public class TestItemPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void una_bebida_se_persiste_correctamente(){
		Item itemInsertado = ItemsFactory.bebida(3,50);
		assertNull(itemInsertado.getId());
		
		entityManager().persist(itemInsertado);
		
	    assertNotNull(itemInsertado.getId());
		
	    Item itemRecuperado = entityManager().find(Item.class, itemInsertado.getId());
	
	    assertEquals(itemInsertado.getId(), itemInsertado.getId());
	    assertSame(itemRecuperado, itemInsertado);
	}
	
	@Test
	public void bebida_se_persiste_correctamente(){
		Item itemInsertado = ItemsFactory.bebida(3,50);
		assertNull(itemInsertado.getId());
		
		EntityManager entityManager = entityManager();
		
		entityManager.persist(itemInsertado);
		
		entityManager.getTransaction().commit();
		
	    assertNotNull(itemInsertado.getId());
		
	    Item itemRecuperado = entityManager().find(Item.class, itemInsertado.getId());
	
	    assertEquals(itemInsertado.getId(), itemInsertado.getId());
	    assertSame(itemRecuperado, itemInsertado);
	}
} */