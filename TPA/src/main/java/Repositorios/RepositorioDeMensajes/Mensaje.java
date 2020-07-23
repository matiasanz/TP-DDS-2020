package Repositorios.RepositorioDeMensajes;

import java.util.List;

import Compra.Compra;
import Usuario.Usuario;

public class Mensaje {
	private Compra compra;
	private String contenido;
	
	public Mensaje(Compra compra, String contenido) {
		this.compra = compra;
		this.contenido = contenido;
	}
	
	public String getContenido() {
		return this.contenido;
	}
	
	private List<Usuario> getUsuariosHabilitadosParaAcceder() {
		return compra.getUsuariosValidadores();
	}
	
	public boolean estaHabilitado(Usuario usuario) {
		return this.getUsuariosHabilitadosParaAcceder().contains(usuario);
	}
}
