package Usuario;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
    //private Tipo tipo; //administrador o validador

    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String contrasenia;

    @ElementCollection
    private List<String> bandejaDeMensajes = new LinkedList<>();

    public String getUsername() {
        return username;
    }
    
    public String getContrasenia() {
        return contrasenia;
    }

    public List<String> getMensajes(){
    	return bandejaDeMensajes;
    }
    
    //Constructor
    public Usuario(String username, String contrasenia) {

        ValidadorUsuario validacion = new ValidadorUsuario();

        this.username = username;

        validacion.validarContrasenia(contrasenia, username);
        this.contrasenia = contrasenia;

    }

    public void cambiarContrasenia(String contrasenia) {

        ValidadorUsuario validacion = new ValidadorUsuario();

        validacion.validarContrasenia(contrasenia, username);
        this.contrasenia = contrasenia;

    }
    
    public void notificarEvento(String mensaje){
    	synchronized(bandejaDeMensajes){    		
    		bandejaDeMensajes.add(mensaje);
    	}
    }

    public boolean equals(Usuario otroUsuario){
        String username = otroUsuario.getUsername();
        String password = otroUsuario.getContrasenia();
        return this.autentica(username,password);
    }

    public boolean autentica(String username, String password){
        return this.username.equals(username) && this.contrasenia.equals(password);
    }
    
    public void vaciarBandeja(){
    	synchronized(bandejaDeMensajes){
    		bandejaDeMensajes.clear();
    	}
    }

}
