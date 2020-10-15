package Repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Entidad.Entidad;
import Factory.EntidadesFactory;
import Factory.UsuariosFactory;

public class TestRepoEntidades
{
	
	public class TestEntidadPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {
		
		RepoEntidadesDB repoEntidades = new RepoEntidadesDB();
		
		Entidad entidadBaseRandom = EntidadesFactory.baseRandom();
		Entidad entidadJuridicaEmpresa = EntidadesFactory.empresaMedianaTramo2(); 
		Entidad entidadJuridicaOSS = EntidadesFactory.getEntidadJuridica();
		
	    @Before
	    public void start() {
	        repoEntidades.agregar(entidadBaseRandom);
	        repoEntidades.agregar(entidadJuridicaEmpresa);
	    }

	    @After
	    public void end() {
	        this.rollbackTransaction();
	    }
		
		@Test
		public void entidadesSeRecuperanCorrectamente(){
			List<Entidad> recuperadas = repoEntidades.getAll();
			assertEquals(2, recuperadas.size());
			
			assertEntidad(entidadBaseRandom, recuperadas.get(0));
			assertEntidad(entidadJuridicaEmpresa, recuperadas.get(1));
		}
		
		@Test
		public void persistenciaEntidadConCompras(){
			repoEntidades.agregar(EntidadesFactory.getEntidadConCompras());	
			assertEquals(3, repoEntidades.getAll().size());
			assertEntidad(entidadJuridicaOSS, repoEntidades.getAll().get(2));
		}
		
		//Auxiliar
		private void assertEntidad(Entidad expected, Entidad actual){
			assertEquals(expected.getId(), actual.getId());
			assertEquals(expected.getNombreFicticio(), actual.getNombreFicticio());
			assertEquals(expected.getCompras().size(), actual.getCompras().size());
			assertEquals(expected.getValorTodasLasCompras(), actual.getValorTodasLasCompras());
		}
	}
	
}
