package db;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Factory.UsuariosFactory;
import Usuario.Usuario;

public class TestUsuarioPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {

	Usuario usuarioAInsertar = UsuariosFactory.usuarioStub();

	@After
	public void finish(){
		this.remove(usuarioAInsertar);
		this.commitTransaction();
	}

	@Test
	  public void persistenciaUsuario() {
	    assertNull(usuarioAInsertar.getId());
	
	    entityManager().persist(usuarioAInsertar);
	
	    assertNotNull(usuarioAInsertar.getId());
	    	    
	    Usuario usuarioRecuperado = entityManager().find(Usuario.class, usuarioAInsertar.getId());
	
	    assertEquals(usuarioRecuperado.getId(), usuarioAInsertar.getId());
	    assertSame(usuarioRecuperado, usuarioAInsertar);
	  }
	
	@Test
	  public void persistenciaUsuarios() {
		assertNull(usuarioAInsertar.getId());
	    
	    EntityManager entityManager = entityManager();
		
		entityManager.persist(usuarioAInsertar);
		
		entityManager.getTransaction().commit();
	
	    assertNotNull(usuarioAInsertar.getId());
	    	    
	    Usuario usuarioRecuperado = entityManager().find(Usuario.class, usuarioAInsertar.getId());
	
	    assertEquals(usuarioRecuperado.getId(), usuarioAInsertar.getId());
	    assertSame(usuarioRecuperado, usuarioAInsertar);	  
	  }
}