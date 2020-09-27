package Compra;

import Entidad.Entidad;
import Entidad.EntidadJuridica;
import MedioDePago.MedioDePago;
import Moneda.CodigoMoneda;
import Moneda.Moneda;
import Presupuesto.Presupuesto;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;
import Usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private Entidad entidadRelacionada;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    //private Documento documentoComercial;

    @Column(name = "fecha_operacion")
    private final LocalDate fechaOperacion;

    @Transient
    private final MedioDePago medioDePago;

    @Column(name = "cantidad_minima_de_presupuestos")
    private int cantidadMinimaDePresupuestos;

    @Embedded
    private final Moneda moneda;

    @Column(name = "indicador_de_aprobacion")
    @Enumerated(EnumType.ORDINAL)
    private Estado indicadorDeAprobacion;

    @ManyToMany
    @JoinTable(name = "items_por_compra")
    private final List<Item> items;

    @OneToMany
    @JoinColumn(name = "compra_id")
    private List<Presupuesto> presupuestosAsociados;

    @ManyToMany
    @JoinTable(name = "validadores_por_compra",
            joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "usuarios_id"))
    private final List<Usuario> usuariosValidadores;

    @Transient
    private final ValidadorDeCompra validadorDeCompra;

    @ElementCollection
    @CollectionTable(name = "etiquetas", joinColumns=@JoinColumn(name = "compra_id"))
    @Column(name = "etiqueta")
    private List<String> etiquetas;

    //Constructor
    public Compra(RepositorioDeMonedas repositorioDeMonedas,
                  EntidadJuridica entidad,
                  Proveedor proveedor,
                  LocalDate fecha,
                  MedioDePago medioDePago,
                  CodigoMoneda codigoMoneda,
                  int cantidadMinimaDePresupuestos,
                  List<Usuario> usuariosValidadores) {

        this.validadorDeCompra = new ValidadorDeCompra();
        this.entidadRelacionada = entidad;
        this.proveedor = proveedor;
        //this.documentoComercial = documentoComercial;
        this.fechaOperacion = fecha;
        this.medioDePago = medioDePago;
        this.cantidadMinimaDePresupuestos = cantidadMinimaDePresupuestos;
        this.moneda = repositorioDeMonedas.getMoneda(codigoMoneda);
        this.indicadorDeAprobacion = Estado.PENDIENTEDEAPROBACION;
        this.items = new ArrayList<>();
        this.presupuestosAsociados = new ArrayList<>();
        this.usuariosValidadores = usuariosValidadores;
        this.etiquetas = new ArrayList<>();
    }

    //Items ***********************************
    
    public List<Item> getItems() {
        return items;
    }
    
    public void agregarItem(Item item) {
        this.items.add(item);
    }
    
    public BigDecimal getValorTotal() {
        return items.stream().map(Item::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
//Presupuesto *****************************

    public int getCantidadMinimaDePresupuestos() {
        return cantidadMinimaDePresupuestos;
    }

    public List<Presupuesto> getPresupuestosAsociados() {
        return presupuestosAsociados;
    }
    
    public void agregarPresupuesto(Presupuesto presupuesto) {
        presupuestosAsociados.add(presupuesto);
    }

    public void setPresupuestoElegido(Presupuesto presupuesto) {
        Presupuesto presupuestoAElegir = presupuestosAsociados.stream().filter(unPresupuesto -> unPresupuesto.equals(presupuesto)).findFirst().orElseThrow(NoHayPresupuestosException::new);
        presupuestoAElegir.setElegido(true);
    }
    
 /* Retorna el presupuesto elegido
    si no hay presupuesto elegido todavía arroja excepcion
 */
    public Presupuesto getPresupuestoElegido() {
        return presupuestosAsociados.stream().filter(unPresupuesto -> unPresupuesto.isElegido()).findFirst().orElseThrow(NoHayPresupuestoElegidoException::new);
    }
    
//Estado de Aprobacion ***************************
    
    public Estado getIndicadorDeAprobacion() {
        return indicadorDeAprobacion;
    }

    public void setIndicadorDeAprobacion(Estado estado) {
        indicadorDeAprobacion = estado;
    }

    public void aprobar() {
        this.indicadorDeAprobacion = Estado.APROBADA;
    }

    public void rechazar() {
        this.indicadorDeAprobacion = Estado.RECHAZADA;
    }

    public boolean pendienteDeAprobacion() {
        return this.indicadorDeAprobacion == Estado.PENDIENTEDEAPROBACION;
    }
    
    public void validar() {
        validadorDeCompra.validarCompra(this);
    }

//Usuarios validadores *****************

    public void agregarUsuarioValidador(Usuario usuario) {
    	usuariosValidadores.add(usuario);
    }
    
    public boolean puedeSerValidadaPor(Usuario miUsuario) {
        return usuariosValidadores.contains(miUsuario);
    }
    
    public void notificarUsuarios(String mensaje){
    	usuariosValidadores.stream().forEach(unUsuario->unUsuario.notificarEvento(mensaje));
    }

//Etiqueta ****************
    
    public List<String> getEtiquetas() {
        return etiquetas;
    }
    
    public void agregarEtiqueta (String etiqueta){
        etiquetas.add(etiqueta);
    }

    public boolean contieneEtiqueta (String etiqueta){
        return etiquetas.contains(etiqueta);
    }

    public boolean etiquetada(){
    	return !etiquetas.isEmpty();
    }
    
//Otros *******************

    public Moneda getMoneda() {
        return this.moneda;
    }
    
    public Long getId(){
		return id;
	}
    
    public boolean compraDelMes(LocalDate unaFecha){
        return this.getFechaOperacion().getMonth().getValue() == unaFecha.getMonth().getValue()
                && getFechaOperacion().getYear() == unaFecha.getYear();
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }
    
    public Entidad getEntidadRelacionada(){
    	return entidadRelacionada;
    }
    
    public MedioDePago getMedioDePago(){
    	return medioDePago;
    }

    public Proveedor getProveedor() { return proveedor; }
}
