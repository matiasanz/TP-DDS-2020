package Organizacion;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import Compra.Compra;
import Entidad.EntidadJuridica;
import Usuario.Usuario;

public class Organizacion {
	private List<Usuario> usuarios = new ArrayList<>();
	private List<EntidadJuridica> listaEntidades =  new ArrayList<>();
	private List<Compra> listaCompras = new ArrayList<>();
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public List<Compra> getCompras(){
		return listaCompras;
	}
	
	public List<EntidadJuridica> getEntidades(){
		return listaEntidades;
	}
	
	public void crearUsuario(String username, String password) {
		boolean usuarioYaExiste = usuarios.stream().anyMatch(u -> username.equals(u.getUsername()));
		if (usuarioYaExiste){
			throw new UsuarioYaExisteException();
		}
		usuarios.add(new Usuario(username, password));
	}
	
	public void agregarEntidad(EntidadJuridica entidad) {
		listaEntidades.add(entidad);
	}
	
	public void agregarCompra(Compra compra) {
		listaCompras.add(compra);
	}
	
	public BigDecimal getValorTodasLasCompras() {
		return listaCompras.stream().map(Compra::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
}
