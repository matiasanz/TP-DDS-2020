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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Compra {
	private RepositorioDeMonedas repositorioDeMonedas;
	private Entidad entidadRelacionada;
	//	private Documento documentoComercial;
	private LocalDate fechaOperacion;
	private MedioDePago medioDePago;
	private int cantidadMinimaDePresupuestos;
	private Moneda moneda;
	private Estado indicadorDeAprobacion;
	private List<Item> items;
	private List<Presupuesto> presupuestosAsociados;
	private Presupuesto presupuestoElegido;
	List<Usuario> usuariosValidadores;
	
	public Compra(RepositorioDeMonedas repositorioDeMonedas,
				  EntidadJuridica entidad,
				  Proveedor proveedor,
				  LocalDate fecha,
				  MedioDePago medioDePago,
				  CodigoMoneda codigoMoneda,
				  int cantidadMinimaDePresupuestos,
				  List<Usuario> usuariosValidadores) {

		this.repositorioDeMonedas = repositorioDeMonedas;
		this.entidadRelacionada = entidad;
		//this.documentoComercial = documentoComercial;
		this.fechaOperacion = fecha;
		this.medioDePago = medioDePago;
		this.cantidadMinimaDePresupuestos = cantidadMinimaDePresupuestos;
		this.moneda = repositorioDeMonedas.getMoneda(codigoMoneda);
		this.indicadorDeAprobacion = Estado.NUEVA;
		this.items = new ArrayList<>();
		this.presupuestosAsociados = new ArrayList<>();
		this.presupuestoElegido = null;
		this.usuariosValidadores = usuariosValidadores;
	}
	
	public BigDecimal getValorTotal() {
		return items.stream().map(Item::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	public void generarPresupuesto(Presupuesto presupuesto){
		presupuestosAsociados.add(presupuesto);
	}


	/*	Elige entre mis presupuestos el más barato
		Si todavía no tengo los presupuestos suficientes arroja excepcion
	 */
	/*public void elegirPresupuestoMasBarato(){
		validarSuficientesPresupuestos();
		Presupuesto presupuestoElegido = presupuestosAsociados.stream().min(Comparator.naturalOrder()).orElseThrow(NoHayPresupuestosException::new);
		this.presupuestoElegido = presupuestoElegido;
		this.presupuestosAsociados.remove(presupuestoElegido);
		this.indicadorDeAprobacion = Estado.PENDIENTEDEAPROBACION;
	}*/

	public void elegirPresupuesto(Presupuesto presupuesto){
		this.presupuestoElegido = presupuesto;
	}

	public void setIndicadorDeAprobacion(Estado estado){
		indicadorDeAprobacion = estado;
	}

	/* Retorna el presupuesto elegido
		si no hay presupuesto elegido todavía arroja excepcion
	 */
	public Presupuesto getPresupuestoElegido(){
		if(indicadorDeAprobacion != Estado.PENDIENTEDEAPROBACION || indicadorDeAprobacion != Estado.APROBADA) {
			return presupuestoElegido;
		}
		else{
			throw new NoHayPresupuestoElegidoException();
		}
	}

	public Estado getIndicadorDeAprobacion(){
		return indicadorDeAprobacion;
	}

	public void agregarItem(Item item){
		this.items.add(item);
	}

	public void validar(){
		validarSuficientesPresupuestos();
		if (cantidadMinimaDePresupuestos > 0){
			validarCompraEnBaseDePresupuesto();
			validarSeleccionDeProveedorSegunCriterio();
		}
	}

	public void validarSuficientesPresupuestos(){
		if (cantidadMinimaDePresupuestos > presupuestosAsociados.size()){
			throw new NoHayPresupuestosSuficientesException();
		}
	}

	public void validarCompraEnBaseDePresupuesto(){
			if (!(this.presupuestosAsociados.contains(this.presupuestoElegido))){
				throw new PresupuestoElegidoNoSeEncuentraEntreLosPresupuestosException();
			}
	}

	public void validarSeleccionDeProveedorSegunCriterio(){
		Presupuesto presupuestoElegido = presupuestosAsociados.stream().min(Comparator.naturalOrder()).orElseThrow(NoHayPresupuestosException::new);
		if (this.presupuestoElegido != presupuestoElegido){
			throw new PresupuestoElegidoNoCumpleCriterioException();
		}
	}

	public Moneda getMoneda(){
		return this.moneda;
	}
}
