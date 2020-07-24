package Compra;

import Entidad.Entidad;
import Entidad.EntidadJuridica;
import Etiqueta.Etiqueta;
import MedioDePago.MedioDePago;
import Moneda.CodigoMoneda;
import Moneda.Moneda;
import Presupuesto.Presupuesto;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeEtiquetas.RepositorioEtiquetas;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;
import Usuario.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compra {
    private RepositorioEtiquetas repositorioDeEtiquetas;
    private Entidad entidadRelacionada;
    //	private Documento documentoComercial;
    private final LocalDate fechaOperacion;
    private final MedioDePago medioDePago;
    private int cantidadMinimaDePresupuestos;
    private final Moneda moneda;
    private Estado indicadorDeAprobacion;
    private final List<Item> items;
    private List<Presupuesto> presupuestosAsociados;
    //private Presupuesto presupuestoElegido;
    private final List<Usuario> usuariosValidadores;
    private final ValidadorDeCompra validadorDeCompra;
    private Etiqueta etiqueta;

    public Compra(RepositorioDeMonedas repositorioDeMonedas,
                  RepositorioEtiquetas repositorioDeEtiquetas,
                  EntidadJuridica entidad,
                  Proveedor proveedor,
                  LocalDate fecha,
                  MedioDePago medioDePago,
                  CodigoMoneda codigoMoneda,
                  int cantidadMinimaDePresupuestos,
                  List<Usuario> usuariosValidadores) {

        this.validadorDeCompra = new ValidadorDeCompra();
        this.entidadRelacionada = entidad;
        //this.documentoComercial = documentoComercial;
        this.fechaOperacion = fecha;
        this.medioDePago = medioDePago;
        this.cantidadMinimaDePresupuestos = cantidadMinimaDePresupuestos;
        this.moneda = repositorioDeMonedas.getMoneda(codigoMoneda);
        this.indicadorDeAprobacion = Estado.PENDIENTEDEAPROBACION;
        this.items = new ArrayList<>();
        this.presupuestosAsociados = new ArrayList<>();
        this.usuariosValidadores = usuariosValidadores;
        this.etiqueta = repositorioDeEtiquetas.getEtiquetaDefecto();
    }

    public BigDecimal getValorTotal() {
        return items.stream().map(Item::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void generarPresupuesto(Presupuesto presupuesto) {
        presupuestosAsociados.add(presupuesto);
    }

    public void setPresupuestoElegido(Presupuesto presupuesto) {
        Presupuesto presupuestoAElegir = presupuestosAsociados.stream().filter(unPresupuesto -> unPresupuesto.equals(presupuesto)).findFirst().orElseThrow(NoHayPresupuestosException::new);
        presupuestoAElegir.setElegido(true);
    }

    public void setIndicadorDeAprobacion(Estado estado) {
        indicadorDeAprobacion = estado;
    }

    /* Retorna el presupuesto elegido
        si no hay presupuesto elegido todavía arroja excepcion
     */
    public Presupuesto getPresupuestoElegido() {
        return presupuestosAsociados.stream().filter(unPresupuesto -> unPresupuesto.isElegido()).findFirst().orElseThrow(NoHayPresupuestoElegidoException::new);
    }

    public void agregarItem(Item item) {
        this.items.add(item);
    }

    public void aprobar() {
        this.indicadorDeAprobacion = Estado.APROBADA;
    }

    public void rechazar() {
        this.indicadorDeAprobacion = Estado.RECHAZADA;
    }

    public void validar() {
        validadorDeCompra.validarCompra(this);
    }

    public Moneda getMoneda() {
        return this.moneda;
    }

    public boolean estaAprobada() {
        return this.indicadorDeAprobacion == Estado.APROBADA;
    }

    public boolean fueRechazada() {
        return this.indicadorDeAprobacion == Estado.RECHAZADA;
    }

    public boolean pendienteDeAprobacion() {
        return this.indicadorDeAprobacion == Estado.PENDIENTEDEAPROBACION;
    }

    public boolean puedeSerValidadaPor(Usuario miUsuario) {
        return usuariosValidadores.contains(miUsuario);
    }

    public void numerarItems() {
        items.forEach(unItem -> System.out.println(" >> " + unItem.toString()));
    }

    public BigDecimal getImporte() {
        return getPresupuestoElegido().getValorTotal();
    }

    public List<Item> getItems() {
        return items;
    }

    public Estado getIndicadorDeAprobacion() {
        return indicadorDeAprobacion;
    }

    public int getCantidadMinimaDePresupuestos() {
        return cantidadMinimaDePresupuestos;
    }

    public void setCantidadMinimaDePresupuestos(int cantidadMinimaDePresupuestos) {
        this.cantidadMinimaDePresupuestos = cantidadMinimaDePresupuestos;
    }

    public List<Presupuesto> getPresupuestosAsociados() {
        return presupuestosAsociados;
    }

    public void setPresupuestosAsociados(List<Presupuesto> presupuestosAsociados) {
        this.presupuestosAsociados = presupuestosAsociados;
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public Entidad getEntidadRelacionada() {
        return entidadRelacionada;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    public boolean compraDelMes(LocalDate unaFecha){
        return this.getFechaOperacion().getMonth().getValue() == unaFecha.getMonth().getValue()
                && getFechaOperacion().getYear() == unaFecha.getYear();
    }
    
    public List<Usuario> getUsuariosValidadores() {
    	return this.usuariosValidadores;
    }
    
    public void agregarUsuarioValidador(Usuario usuario) {
    	usuariosValidadores.add(usuario);
    }
}
