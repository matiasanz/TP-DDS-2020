package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Entidad.Entidad;
import Factory.EntidadesFactory;
import Factory.UsuariosFactory;


public class TestEntidadPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void persistenciaEntidadBase(){
		Entidad entidadInsertada = EntidadesFactory.baseRandom();
		testPersistencia(entidadInsertada);
	}
	
	
	@Test
	public void persistenciaEntidadJuridicaEmpresaMediana(){
		Entidad entidadInsertada = EntidadesFactory.empresaMedianaTramo2();
		testPersistencia(entidadInsertada);
	}
	
	@Test
	public void persistenciaEntidadJuridicaSectorSocial(){
		Entidad entidadInsertada = EntidadesFactory.getEntidadJuridica();
		testPersistencia(entidadInsertada);
	}
	
	@Test
	public void persistenciaEntidadConCompras(){
		Entidad entidadInsertada = EntidadesFactory.entidadConComprasParaUsuario(UsuariosFactory.usuarioStub());
		testPersistencia(entidadInsertada);
	}
	
	//Auxiliar
	private void testPersistencia(Entidad entidadInsertada){
		assertNull(entidadInsertada.getId());
		
		entityManager().persist(entidadInsertada);
		
	    assertNotNull(entidadInsertada.getId());
		
	    Entidad entidadRecuperada = entityManager().find(Entidad.class, entidadInsertada.getId());
	
	    assertEquals(entidadInsertada.getId(), entidadInsertada.getId());
	    assertSame(entidadRecuperada, entidadInsertada);
	}
}

