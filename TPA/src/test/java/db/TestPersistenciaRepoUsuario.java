package db;

import static org.junit.Assert.*;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Factory.UsuariosFactory;
import Organizacion.IngresoFallidoException;
import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import Usuario.Usuario;

public class TestPersistenciaRepoUsuario extends AbstractPersistenceTest implements WithGlobalEntityManager {
	RepoUsuariosDB repo;
	Usuario usuario = UsuariosFactory.usuarioStub();
	
	@Before
	public void init(){
		repo = new RepoUsuariosDB(new BasicDataSource());
		// Lo hice asi nomas. Ver como es para que se relacione con nuestra db

		repo.agregarUsuario(usuario);
	}
	
	@Test (expected = IngresoFallidoException.class)
	public void seIngresanDatosIncorrectosYNoEncuentraNingunUsuario(){
		repo.getUsuario("Juan Carlos","intento 34.561 de fuerza bruta");
	}
	
	@Test
    public void usuarioSeRecuperaDeCache(){
		Usuario recuperado = repo.getUsuario(usuario.getUsername(),usuario.getContrasenia());
		assertSame(usuario, recuperado);
	}

//TODO
//	@Test
//	public void usuarioSeRecuperaDeBD(){
//		repo.actualizarBaseDeDatos(); 
//		//Todavia no hace nada
//
//		Usuario recuperado = repo.getUsuarioFromDB(usuario.getUsername(),usuario.getContrasenia());
//	
//		assertNotNull(recuperado);
//		assertSame(usuario, recuperado);
//	}
}
