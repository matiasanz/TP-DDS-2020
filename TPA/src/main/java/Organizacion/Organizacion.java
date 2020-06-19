package Organizacion;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Compra.Compra;
import Entidad.Entidad;
import Entidad.EntidadJuridica;
import Usuario.Tipo;
import Usuario.Usuario;

public class Organizacion {
	private List<Usuario> usuarios = new ArrayList<>();
	private List<Entidad> listaEntidades = new ArrayList<>(); //(?) nombre
	private List<Compra> listaCompras = new ArrayList<>(); //(?) nombre

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Compra> getCompras() {
		return listaCompras;
	}

	public List<Entidad> getEntidades() {
		return listaEntidades;
	}

	public void agregarEntidad(EntidadJuridica entidad) {
		listaEntidades.add(entidad);
	}

	public void agregarCompra(Compra compra) {
		listaCompras.add(compra);
	}

	public void crearUsuario(String username, String password) {
		boolean usuarioYaExiste = usuarios.stream().anyMatch(u -> username.equals(u.getUsername()));
		if (usuarioYaExiste) {
			throw new UsuarioYaExisteException();
		}
		usuarios.add(new Usuario(username, password));
	}

	public BigDecimal getValorTodasLasCompras() {
		return listaCompras.stream().map(Compra::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	//************** Para main ****************
	
	private Usuario getUsuario(String username, String password){
		//No encontre otra forma
		return usuarios.stream().filter(usuario->usuario.autentica(username,password)).findFirst().get();
	}
	
	public Usuario ingresarUsuario(){
    	Scanner entrada = new Scanner(System.in);
    	System.out.println("Ingrese Usuario y contraseña");
    	String username = entrada.next();
    	String password = entrada.next();
    	entrada.close();
    	
    	this.autenticarUsuario(username,password);
    	
    	return this.getUsuario(username,password);
	}
		
	public void autenticarUsuario(String username, String password){
		if(!usuarios.stream().anyMatch(usuario -> usuario.autentica(username, password))){
			throw new IngresoFallidoException();
		}
	}
	
	public void validarCompras(){
		this.comprasPendientesDeValidacion().forEach(unaCompra->unaCompra.validar());
	}
	
	private Stream<Compra> comprasPendientesDeValidacion(){
		return this.listaCompras.stream().filter(unaCompra -> unaCompra.pendienteDeAprobacion());
	}
	
	public List<Compra> comprasQuePuedeValidar(Usuario miUsuario){
		Stream<Compra> comprasPorValidar = this.comprasPendientesDeValidacion();
		return comprasPorValidar.filter(unaCompra->unaCompra.puedeSerValidadaPor(miUsuario)).collect(Collectors.toList());
	}
	
	public List<Compra> getComprasAprobadas(){
		return listaCompras.stream().filter(Compra::estaAprobada).collect(Collectors.toList());
	}
	
	public List<Compra> getComprasRechazadas(){
		return listaCompras.stream().filter(Compra::fueRechazada).collect(Collectors.toList());		
	}
}
