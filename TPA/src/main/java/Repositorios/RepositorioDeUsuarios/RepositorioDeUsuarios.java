package Repositorios.RepositorioDeUsuarios;

import Organizacion.IngresoFallidoException;
import Organizacion.UsuarioYaExisteException;
import Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioDeUsuarios {
    List<Usuario> usuarios = new ArrayList();

    public boolean checkSiExiste(String username){
        return usuarios.stream().anyMatch(u -> username.equals(u.getUsername()));
    }

    public void agregarUsuario(String username, String password){
		this.validarNoRepetido(username);
        usuarios.add(new Usuario(username, password));
    }
    
    public void autenticarUsuario(String username, String password){
        if(usuarios.stream().noneMatch(usuario -> usuario.autentica(username, password))){
            throw new IngresoFallidoException();
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public Usuario getUsuario(String nombre, String password){
    	this.autenticarUsuario(nombre,password);
    	return this.usuarios
    			   .stream()
    			   .filter(usuario -> usuario.autentica(nombre,password))
    			   .findAny()
    			   .orElse(null);
    }
    
    public void validarNoRepetido(String username){
    	if (this.checkSiExiste(username)) {
			throw new UsuarioYaExisteException();
		}
    }
    
    public void validarExistencia(String username){
    	if(!this.checkSiExiste(username)){
    		throw new UsuarioDesconocidoException(username);
    	}
    }
}
