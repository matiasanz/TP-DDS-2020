package Repositorios.RepositorioDeUsuarios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Organizacion.UsuarioYaExisteException;
import Usuario.Usuario;

public class RepoUsuariosDB{

	@SuppressWarnings("unchecked")
	public	List<Usuario> getUsuarios(){
		return createQuery().getResultList();
	}
		
	public Usuario getUsuario(String nombre){
		Usuario usuario; 
		try{
			usuario = (Usuario) createQuery("where username = :user")
				.setParameter("user", nombre)
				.getSingleResult();
		} 
		
		catch(NoResultException e){			
			throw new UsuarioNoExisteException(nombre);
		}
		
		return usuario;
	}
	
	public void agregarUsuario(Usuario usuario){
		validarNoRepetido(usuario.getUsername());
		entityManager().persist(usuario);
	}
	
	public void eliminarUsuario(Usuario usuario){
		entityManager().remove(usuario);
	}
	
	public boolean nombreOcupado(String username){
        try{
        	getUsuario(username);
        	return true;
        } catch (UsuarioNoExisteException e){
        	return false;
        }
    }
	
    public void validarNoRepetido(String username){
    	if (this.nombreOcupado(username)) {
			throw new UsuarioYaExisteException();
		}
    }

// Auxiliares *********************************
    
	private Query createQuery(){
		return createQuery("");
	}
	
	private Query createQuery(String where){
		return entityManager().createQuery("from Usuario " + where);
	}
	
	private EntityManager entityManager(){
		return PerThreadEntityManagers.getEntityManager();
	}
}

