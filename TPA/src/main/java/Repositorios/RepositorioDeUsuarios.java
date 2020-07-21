package Repositorios;

import Organizacion.IngresoFallidoException;
import Usuario.Usuario;

import java.util.List;

public class RepositorioDeUsuarios {
    List<Usuario> usuarios;

    public boolean checkSiExiste(String username){
        return usuarios.stream().anyMatch(u -> username.equals(u.getUsername()));
    }

    public void agregarUsuario(String username, String password){
        usuarios.add(new Usuario(username, password));
    }
    public void autenticarUsuario(String username, String password){
        if(!usuarios.stream().anyMatch(usuario -> usuario.autentica(username, password))){
            throw new IngresoFallidoException();
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
