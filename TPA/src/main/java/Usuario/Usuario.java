package Usuario;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

import Exceptions.ErrorDeAutenticacionException;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String contrasenia;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn(name="posicion")
    @JoinTable(name = "mensajes_por_usuario")
    private List<Mensaje> bandejaDeMensajes = new LinkedList<>();

    @Enumerated
    private TipoUsuario tipo;
    
    public Usuario() {}

    public Usuario(String username, String contrasenia, TipoUsuario tipoUsuario) {
    	this.username = username;
        this.contrasenia = contrasenia;
        this.tipo = tipoUsuario;
        this.bandejaDeMensajes = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }
    
    public String getContrasenia() {
        return contrasenia;
    }

    public Long getId(){
    	return id;
    }

    public TipoUsuario getTipo(){
    	return tipo;
    }

    public List<Mensaje> getBandejaDeMensajes() {
        return bandejaDeMensajes;
    }

    public void setBandejaDeMensajes(List<Mensaje> bandejaDeMensajes) {
        this.bandejaDeMensajes = bandejaDeMensajes;
    }

    public void cambiarContrasenia(String contrasenia) {

        ValidadorUsuario validacion = new ValidadorUsuario();

        validacion.validarContrasenia(contrasenia, username);
        this.contrasenia = contrasenia;

    }
    
    public void notificarEvento(Mensaje mensaje){
    	bandejaDeMensajes.add(mensaje);
    }

    public void autenticar(String password){
    	if(!contrasenia.equals(password)) {
    		throw new ErrorDeAutenticacionException();
    	}
    }

    
    //Deprecated
    public boolean equals(Usuario otroUsuario){
        return this.autentica(otroUsuario.getUsername(),otroUsuario.getContrasenia());
    }

    public boolean autentica(String username, String password){
        return this.username.equals(username) && this.contrasenia.equals(password);
    }
    //******************************
    
    public void vaciarBandeja(){
    	bandejaDeMensajes.clear();
    }

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String nombre){
		username = nombre;
	}
	
	public void setContrasenia(String password){
		contrasenia = password;
	}

	public void setTipo(TipoUsuario nuevoTipo)
	{
		tipo = nuevoTipo;
	}
}
