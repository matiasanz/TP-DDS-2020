package Organizacion;

import java.util.List;
import java.math.BigDecimal;
import java.util.stream.Collectors;

import Compra.Compra;
import Compra.ValidadorDeCompra;
import Entidad.Entidad;
import Entidad.EntidadJuridica;
import Repositorios.RepositorioDeCategorias;
import Repositorios.RepositorioDeEntidades;
import Repositorios.RepositorioDeUsuarios.RepositorioDeUsuarios;
import Usuario.Usuario;

public class Organizacion {
    private RepositorioDeCategorias repositorioDeCategorias;
    private RepositorioDeUsuarios repositorioDeUsuarios;
    private RepositorioDeEntidades repositorioDeEntidades;
    private ValidadorDeCompra validadorDeCompra = new ValidadorDeCompra();
    
    public Organizacion(RepositorioDeUsuarios repoDeUsuarios, RepositorioDeCategorias repoDeCategorias, RepositorioDeEntidades repoDeEntidades) {
        this.repositorioDeUsuarios = repoDeUsuarios;
        this.repositorioDeCategorias = repoDeCategorias;
        this.repositorioDeEntidades = repoDeEntidades;
    }

    public List<Usuario> getUsuarios(){
    	return repositorioDeUsuarios.getUsuarios();
    }
    
    public RepositorioDeUsuarios getRepoUsuarios() {
        return repositorioDeUsuarios;
    }

    public RepositorioDeEntidades getRepoEntidades(){
    	return repositorioDeEntidades;
    }
    
    public Usuario getUsuarioEspecifico(String nombreDeUsuario) {
    	return repositorioDeUsuarios.getUsuario(nombreDeUsuario);
    }
    
    public List<Entidad> getEntidades() {
        return repositorioDeEntidades.getAll();
    }

    public void agregarEntidad(Entidad entidad) {
        repositorioDeEntidades.agregar(entidad);
    }

    public void crearUsuario(String username, String password) {
    	Usuario usuario = new Usuario(username, password, null);
        repositorioDeUsuarios.add(usuario);
    }

    public void autenticarUsuario(String username, String password) {
        repositorioDeUsuarios.autenticarUsuario(username, password);
    }

    public List<Compra> getCompras() {
        return this.getEntidades().stream().map(entidad -> entidad.getCompras()).flatMap(List::stream).collect(Collectors.toList());
    }

    public BigDecimal getValorTodasLasCompras() {
        return getCompras().stream().map(Compra::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void validarCompras() {
        this.comprasPendientesDeValidacion().forEach(unaCompra -> validadorDeCompra.validar(unaCompra));
    }

    public List<Compra> comprasPendientesDeValidacion() {
        return this.getEntidades().stream().map(entidad -> entidad.getComprasPendientesDeValidacion()).flatMap(List::stream).collect(Collectors.toList());
    }

    public List<Compra> comprasQuePuedeValidar(Usuario miUsuario) {
        List<Compra> comprasPorValidar = this.comprasPendientesDeValidacion();
        return comprasPorValidar.stream().filter(unaCompra -> unaCompra.puedeSerValidadaPor(miUsuario)).collect(Collectors.toList());
    }

}
