package Repositorios.RepositorioDeUsuarios;
import javax.persistence.NoResultException;

import Organizacion.UsuarioYaExisteException;
import Repositorios.RepoDB;
import Usuario.Usuario;

public class RepoUsuariosDB extends RepoDB<Usuario>{
	
	@Override
	protected String className(){
		return "Usuario";
	}
	
	public Usuario getByUsername(String nombre){
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
	
	public Usuario getByID(Long id){
		Usuario usuario;  
		try{
			usuario = (Usuario) createQuery("where id = :user_id")
				.setParameter("user_id", id)
				.getSingleResult();
		} 
		
		catch(NoResultException e){			
			throw new UsuarioNoExisteException();
		}
		
		return usuario;
	}
	
	@Override
	public void agregar(Usuario usuario){
		validarNoRepetido(usuario.getUsername());
		super.agregar(usuario);
	}
	
	public boolean nombreOcupado(String username){
        try{
        	getByUsername(username);
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
}

