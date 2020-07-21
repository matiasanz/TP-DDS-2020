package Organizacion;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Compra.Compra;
import Entidad.Entidad;
import Entidad.EntidadJuridica;
import Repositorios.RepositorioDeCategorias;
import Repositorios.RepositorioDeUsuarios;
import Usuario.Usuario;

public class Organizacion {
	private RepositorioDeCategorias repositorioDeCategorias;
	private RepositorioDeUsuarios repositorioDeUsuarios;
	private List<Entidad> entidades = new ArrayList<>(); //(?) nombre

	public Organizacion(RepositorioDeCategorias repositorioDeCategorias) {
		this.repositorioDeCategorias = repositorioDeCategorias;
	}

	public List<Usuario> getUsuarios() {
		return repositorioDeUsuarios.getUsuarios();
	}

	public List<Entidad> getEntidades() {
		return entidades;
	}

	public void agregarEntidad(EntidadJuridica entidad) {
		entidades.add(entidad);
	}

	public void crearUsuario(String username, String password) {
		boolean usuarioYaExiste = repositorioDeUsuarios.checkSiExiste(username);
		if (usuarioYaExiste) {
			throw new UsuarioYaExisteException();
		}
		repositorioDeUsuarios.agregarUsuario(username, password);
	}
		
	public void autenticarUsuario(String username, String password){
		repositorioDeUsuarios.autenticarUsuario(username, password);
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
