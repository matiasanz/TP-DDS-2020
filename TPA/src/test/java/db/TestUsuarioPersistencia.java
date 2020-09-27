package db;
import static org.junit.Assert.*;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Factory.UsuariosFactory;
import Usuario.Usuario;

public class TestUsuarioPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	  public void persistenciaUsuario() {
	    Usuario usuarioAInsertar = UsuariosFactory.usuarioStub();
	
	    assertNull(usuarioAInsertar.getId());
	
	    entityManager().persist(usuarioAInsertar);
	
	    assertNotNull(usuarioAInsertar.getId());
	    	    
	    Usuario usuarioRecuperado = entityManager().find(Usuario.class, usuarioAInsertar.getId());
	
	    assertEquals(usuarioRecuperado.getId(), usuarioAInsertar.getId());
	    assertSame(usuarioRecuperado, usuarioAInsertar);	  
	  }
}