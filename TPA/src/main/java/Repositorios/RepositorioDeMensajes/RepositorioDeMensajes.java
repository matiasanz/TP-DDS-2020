package Repositorios.RepositorioDeMensajes;

import java.util.List;
import java.util.stream.Collectors;

import Usuario.Usuario;


public class RepositorioDeMensajes {
    private List<Mensaje> mensajes;
    
    public List<Mensaje> getMensajes(){
    	return mensajes;
    }
    
    public List<Mensaje> getMensajesUsuario(Usuario usuario) {
    	return mensajes.stream().filter(mensaje -> mensaje.estaHabilitado(usuario)).collect(Collectors.toList());
    }
    
    public void nuevoMensaje(Mensaje mensaje) {
    	mensajes.add(mensaje);
    }
}