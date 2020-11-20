package Repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Entidad.Entidad;
import Factory.EntidadesFactory;
import TareasProgramadas.ReporteMensualGastos.ReporteMensualDeGastos;

public class TestRepoEntidades
{
	
	public class TestEntidadPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {
		
		RepoEntidadesDB repoEntidades = new RepoEntidadesDB();
		
		Entidad entidadBaseRandom = EntidadesFactory.baseRandom();
		Entidad entidadJuridicaEmpresa = EntidadesFactory.empresaMedianaTramo2(); 
		Entidad entidadJuridicaOSS = EntidadesFactory.getEntidadJuridica();
		
	    @Before
	    public void start() {
	        repoEntidades.salvar(entidadBaseRandom);
	        repoEntidades.salvar(entidadJuridicaEmpresa);
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
			repoEntidades.salvar(EntidadesFactory.getEntidadConCompras());	
			assertEquals(3, repoEntidades.getAll().size());
			assertEntidad(entidadJuridicaOSS, repoEntidades.getAll().get(2));
		}
		
		@Test
		public void reporteDeGastosParaEntidadesPersistidas(){
			repoEntidades.salvar(EntidadesFactory.getEntidadConCompras());
			
			LocalDate fecha = LocalDate.of(2020,07,02);
			List<Map<String, Double>> reportesGenerados = new ArrayList<>();
			repoEntidades.getAll().forEach(e->reportesGenerados.add(ReporteMensualDeGastos.generarReporteDeGastos(e,fecha)));
			
			assertEquals(3, reportesGenerados.size());
			Map<String, Double> gastos = reportesGenerados.get(0);
			assertEquals(0, gastos.size());
			
			//(...)
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
