package Usuario;
import static org.junit.Assert.*;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Factory.UsuariosFactory;

public class TestPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	  public void test() {
	    Usuario usuarioAInsertar = UsuariosFactory.usuarioStub();

	    assertNull(usuarioAInsertar.getId());

	    entityManager().persist(usuarioAInsertar);

	    assertNotNull(usuarioAInsertar.getId());
	    
	    // agregamos una nueva instancia:
	    Usuario otroUsuario = new Usuario("Carol", "1qazxsw23edcvfr45tgbn");
	    
	    entityManager().persist(otroUsuario);

	    assertNotNull(usuarioAInsertar.getId()); 
	    assertNotEquals(usuarioAInsertar.getId(), otroUsuario.getId());
	    
	    Usuario usuarioRecuperado = entityManager().find(Usuario.class, usuarioAInsertar.getId());

	    assertEquals(usuarioRecuperado.getId(), usuarioAInsertar.getId()); // esto no debería resultar extraño
	    assertSame(usuarioRecuperado, usuarioAInsertar); // y esto tampoco, al fin y al cabo estamos recuperando un objeto que ya tenemos, ¿no?
	   
//*********** (??)
//	    // ¿Pero qué pasa si limpio en el medio el entity manager? 
//	    entityManager().clear(); 
//
//	    Usuario usuarioRecuperadoDespuesDeVaciar = entityManager().find(Usuario.class, usuarioAInsertar.getId());
//
//	    assertEquals(usuarioRecuperadoDespuesDeVaciar.getId(), usuarioAInsertar.getId()); // bien
//	    assertNotSame(usuarioRecuperadoDespuesDeVaciar, usuarioAInsertar); 
//	  
	  }
	}