package Organizacion;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

import Compra.Compra;
import Entidad.Entidad;
import Entidad.EntidadJuridica;
import Usuario.Usuario;

public class Organizacion {
	private List<Usuario> usuarios = new ArrayList<>();
	private List<Entidad> listaEntidades =  new ArrayList<>();
	private List<Compra> listaCompras = new ArrayList<>();
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	public List<Compra> getCompras(){
		return listaCompras;
	}
	public List<Entidad> getEntidades(){
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
		if (usuarioYaExiste){
			throw new UsuarioYaExisteException();
		}
		usuarios.add(new Usuario(username, password));
	}

	public BigDecimal getValorTodasLasCompras() {
		return listaCompras.stream().map(Compra::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	/* Esto es medio en vano
		ya estoy haciendo esa validación antes de indicar que esta aprobada
		pero bueno
	 */
	public void validarAprobadasTenganSuficientesPresupuestos() {
		List<Compra> comprasAprobadas = listaCompras.stream().filter(c -> c.getIndicadorDeAprobacion()).collect(Collectors.toList());
		comprasAprobadas.stream().forEach(c -> c.validarSuficientesPresupuestos());
	}
}
