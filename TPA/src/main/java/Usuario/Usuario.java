package Usuario;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    //private Tipo tipo; //administrador o validador

    @Id
    @GeneratedValue
    private Long id ;

    private String username;
    private String contrasenia;

    @ElementCollection
    @Column(name = "Mensajes")
    private List<String> bandejaDeMensajes = new ArrayList<>();

    public String getUsername() {
        return username;
    }
    
    public String getContrasenia() {
        return contrasenia;
    }

    public List<String> getMensajes(){
    	return bandejaDeMensajes;
    }

    public Long getId(){
    	return id;
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
        return this.autentica(otroUsuario.getUsername(),otroUsuario.getContrasenia());
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
