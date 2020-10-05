package Repositorios.RepositorioDeUsuarios;

import Organizacion.IngresoFallidoException;
import Organizacion.UsuarioYaExisteException;
import Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeUsuarios {
    List<Usuario> usuarios = new ArrayList<Usuario>();

    public boolean nombreOcupado(String username){
        return usuarios.stream().anyMatch(u -> username.equals(u.getUsername()));
    }

    public void add(Usuario usuario){
		this.validarNoRepetido(usuario.getUsername());
        usuarios.add(usuario);
    }

    public void eliminarUsuario(Usuario usuario){
        this.usuarios.remove(usuario);
    }

    public void autenticarUsuario(String username, String password){
        if(usuarios.stream().noneMatch(usuario -> usuario.autentica(username, password))){
            throw new IngresoFallidoException();
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public Usuario getUsuario(String nombre){
    	Usuario unUsuario = this.usuarios
    			   .stream()
    			   .filter(usuario -> usuario.getUsername().equals(nombre))
    			   .findAny()
    			   .orElse(null);
    	
    	if(unUsuario==null){
    		throw new UsuarioNoExisteException(nombre);
    	}
    	
    	return unUsuario;
    }
    
    public void validarNoRepetido(String username){
    	if (this.nombreOcupado(username)) {
			throw new UsuarioYaExisteException();
		}
    }
}
