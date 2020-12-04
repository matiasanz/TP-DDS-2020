package Repositorios;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Exceptions.UsuarioNoExisteException;
import Exceptions.UsuarioYaExisteException;
import Factory.UsuariosFactory;
import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import Usuario.Usuario;

public class TestPersistenciaRepoUsuario extends AbstractPersistenceTest implements WithGlobalEntityManager {
	RepoUsuariosDB repo =new RepoUsuariosDB();
	Usuario usuario = UsuariosFactory.usuarioStub();
	
	@Before
	public void init(){
		this.beginTransaction();
		repo.salvar(usuario);
	}

	@After
	public void finish(){
		this.rollbackTransaction();
	}
	
	@Test (expected = UsuarioNoExisteException.class)
	public void seIngresanDatosIncorrectosYNoEncuentraNingunUsuario(){
		repo.getUsuario("Juan Carlos");
	}

	@Test
	public void seRecuperaCantidadIngresada(){
		assertEquals(1, repo.getAll().size());
	}
	
	@Test
	public void usuarioSeRecuperaDeBD(){

		Usuario recuperado = repo.getUsuario(usuario.getUsername());
	
		assertNotNull(recuperado);
		assertEquals(usuario, recuperado);
		assertEquals(usuario.getContrasenia(), recuperado.getContrasenia());
	}
	
	@Test (expected = UsuarioYaExisteException.class)
	public void intentoAgregarUnUsuarioRepetidoYFallo(){
		repo.salvar(usuario);
	}
	
	@Test
	public void nombreDeUsuarioEstaOcupadoSoloSiExiste(){
		String username = usuario.getUsername();
		assert(repo.nombreOcupado(username));
		repo.eliminarA(usuario);
		assertFalse(repo.nombreOcupado(username));
	}
}
