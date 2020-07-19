package Organizacion;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Compra.Compra;
import Entidad.Entidad;
import Entidad.EntidadJuridica;
import Usuario.Usuario;

public class Organizacion {
	private List<Usuario> usuarios = new ArrayList<>();
	private List<Entidad> entidades = new ArrayList<>(); //(?) nombre

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Entidad> getEntidades() {
		return entidades;
	}

	public void agregarEntidad(EntidadJuridica entidad) {
		entidades.add(entidad);
	}

	public void crearUsuario(String username, String password) {
		boolean usuarioYaExiste = usuarios.stream().anyMatch(u -> username.equals(u.getUsername()));
		if (usuarioYaExiste) {
			throw new UsuarioYaExisteException();
		}
		usuarios.add(new Usuario(username, password));
	}
		
	public void autenticarUsuario(String username, String password){
		if(!usuarios.stream().anyMatch(usuario -> usuario.autentica(username, password))){
			throw new IngresoFallidoException();
		}
	}

	public List<Compra> getCompras(){
		return entidades.stream().map(entidad -> entidad.getCompras()).flatMap(List::stream).collect(Collectors.toList());
	}

	public BigDecimal getValorTodasLasCompras() {
		return getCompras().stream().map(Compra::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public void validarCompras(){
		this.comprasPendientesDeValidacion().forEach(unaCompra->unaCompra.validar());
	}

	private Stream<Compra> comprasPendientesDeValidacion(){
		return getCompras().stream().filter(unaCompra -> unaCompra.pendienteDeAprobacion());
	}

	public List<Compra> comprasQuePuedeValidar(Usuario miUsuario){
		Stream<Compra> comprasPorValidar = this.comprasPendientesDeValidacion();
		return comprasPorValidar.filter(unaCompra->unaCompra.puedeSerValidadaPor(miUsuario)).collect(Collectors.toList());
	}
}
