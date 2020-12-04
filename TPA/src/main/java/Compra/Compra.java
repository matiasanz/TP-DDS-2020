package Compra;

import Entidad.Entidad;
import Factory.MensajeFactory;
import MedioDePago.MedioDePago;
import Moneda.CodigoMoneda;
import Moneda.Moneda;
import Presupuesto.Presupuesto;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;
import Usuario.Usuario;
import Usuario.Mensaje;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "entidad_id")
    private Entidad entidadRelacionada;

    //TODO
    //private Documento documentoComercial;

    @Column(name = "fecha_operacion")
    private  LocalDate fechaOperacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medio_de_pago_id")
    private MedioDePago medioDePago;

    @Column(name = "cantidad_minima_de_presupuestos")
    private int cantidadMinimaDePresupuestos;

    @Embedded
    private  Moneda moneda;

    @Column(name = "indicador_de_aprobacion")
    @Enumerated(EnumType.ORDINAL)
    private Estado indicadorDeAprobacion = Estado.PENDIENTEDEAPROBACION;

    @Transient
    private  List<Item> items = new ArrayList<>();;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "compra_id")
    private List<Presupuesto> presupuestosAsociados = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "validadores_por_compra",
            joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "usuarios_id"))
    private  List<Usuario> usuariosValidadores = new LinkedList<>();

    @ElementCollection
    @CollectionTable(name = "etiquetas", joinColumns=@JoinColumn(name = "compra_id"))
    @Column(name = "etiqueta")
    private List<String> etiquetas = new ArrayList<>();

    public Compra() {}

    public Compra(RepositorioDeMonedas repositorioDeMonedas,
                  Entidad entidad,
                  /*DocumentoCompercial documentoComercial,*/
                  LocalDate fecha,
                  MedioDePago medioDePago,
                  CodigoMoneda codigoMoneda,
                  int cantidadMinimaDePresupuestos) {

        this.entidadRelacionada = entidad;
        //this.documentoComercial = documentoComercial; //TODO importante entrega 2
        this.fechaOperacion = fecha;
        this.medioDePago = medioDePago;
        this.cantidadMinimaDePresupuestos = cantidadMinimaDePresupuestos;
        this.moneda = repositorioDeMonedas.getMoneda(codigoMoneda);
    }



	//Items ***********************************

    public List<Item> getItems() {
    	try{
    		return getPresupuestoElegido().getItems();
    	} catch( NoHayPresupuestoElegidoException e){
    		return items;
    	}
    }

    public void agregarItem(Item item) {
        this.items.add(item);
    }

    public BigDecimal getValorTotal() {
        return getItems().stream().map(Item::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
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
        this.setItems(presupuestoAElegir.getItems()); //TODO
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
        this.notificarAprobacion();
    }

    private void notificarAprobacion() {
        this.usuariosValidadores.forEach(u -> u.notificarEvento(MensajeFactory.mensajeDeAprobacion(this)));
    }

    public void rechazar(String motivoRechazo) {

        this.indicadorDeAprobacion = Estado.RECHAZADA;
        this.notificarRechazo(motivoRechazo);
    }

    private void notificarRechazo(String motivoRechazo) {
        this.usuariosValidadores.forEach(u -> u.notificarEvento(MensajeFactory.mensajeDeRechazo(this, motivoRechazo)));
    }

    public boolean pendienteDeAprobacion() {
        return this.indicadorDeAprobacion == Estado.PENDIENTEDEAPROBACION;
    }

//Usuarios validadores *****************

    public void agregarUsuarioValidador(Usuario usuario) {
    	usuariosValidadores.add(usuario);
    }

    public boolean puedeSerValidadaPor(Usuario miUsuario) {
        return usuariosValidadores.contains(miUsuario);
    }

    public void notificarUsuarios(String mensaje){
    	usuariosValidadores.stream().forEach(unUsuario->unUsuario.notificarEvento(new Mensaje(LocalDateTime.now(), mensaje,0)));
    }

//Etiqueta ****************

    public List<String> getEtiquetas() {
        return etiquetas;
    }

    public void agregarEtiqueta (String etiqueta){
        etiquetas.add(etiqueta);
    }

    public void eliminarEtiqueta (String etiqueta){
        etiquetas.remove(etiqueta);
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

    public Proveedor getProveedor() { return getPresupuestoElegido().getProveedorAsociado(); }

	public void setId(Long id) {
		this.id = id;
	}

	public void setEntidadRelacionada(Entidad entidadRelacionada) {
		this.entidadRelacionada = entidadRelacionada;
	}

	/*public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}*/

	public void setFechaOperacion(LocalDate fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public void setCantidadMinimaDePresupuestos(int cantidadMinimaDePresupuestos) {
		this.cantidadMinimaDePresupuestos = cantidadMinimaDePresupuestos;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setPresupuestosAsociados(List<Presupuesto> presupuestosAsociados) {
		this.presupuestosAsociados = presupuestosAsociados;
	}

	public List<Usuario> getUsuariosValidadores() {
		return usuariosValidadores;
	}

	public void setUsuariosValidadores(List<Usuario> usuariosValidadores) {
		this.usuariosValidadores = usuariosValidadores;
	}

	public void setEtiquetas(List<String> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}
}
