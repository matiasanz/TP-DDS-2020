package Compra;

import Entidad.Entidad;
import Entidad.EntidadJuridica;
import MedioDePago.MedioDePago;
import Moneda.CodigoMoneda;
import Moneda.Moneda;
import Moneda.RepositorioDeMoneda;
import Presupuesto.Presupuesto;
import Proveedor.Proveedor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Compra {
	private RepositorioDeMoneda repositorioDeMoneda;
	private Entidad entidadRelacionada;
	//	private Documento documentoComercial;
	private LocalDate fechaOperacion;
	private MedioDePago medioDePago;
	private int cantidadMinimaDePresupuestos;
	private Moneda moneda;
	private boolean indicadorDeAprobacion;
	private List<Item> items;
	private List<Presupuesto> presupuestosAsociados;
	
	public Compra(EntidadJuridica entidad, Proveedor proveedor, LocalDate fecha,
				  MedioDePago medioDePago, CodigoMoneda codigoMoneda, int cantidadMinimaDePresupuestos) {

		//this.repositorioDeMoneda = TODO
		this.entidadRelacionada = entidad;
		//this.documentoComercial = documentoComercial;
		this.fechaOperacion = fecha;
		this.medioDePago = medioDePago;
		this.cantidadMinimaDePresupuestos = cantidadMinimaDePresupuestos;
		//this.moneda = repositorioDeMoneda.getMoneda(codigoMoneda); TODO
		this.indicadorDeAprobacion = false;
		this.items = new ArrayList<>();
		this.presupuestosAsociados = new ArrayList<>();
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
	public void elegirPresupuestoMasBarato(){
		validarSuficientesPresupuestos();
		Presupuesto presupuestoElegido = presupuestosAsociados.stream().min(Comparator.naturalOrder()).orElseThrow(NoHayPresupuestosException::new);
		setearCompraComoAprobada(presupuestoElegido);
		int index = presupuestosAsociados.indexOf(presupuestoElegido);
		presupuestosAsociados.set(index, presupuestoElegido);
	}

	private void setearCompraComoAprobada(Presupuesto presupuestoElegido) {
		presupuestoElegido.setIndicadorDeAprobacion(true);
		indicadorDeAprobacion = true;
	}

	/* Retorna el presupuesto elegido
		si no hay presupuesto elegido todavía arroja excepcion
	 */
	public Presupuesto getPresupuestoElegido(){
		return presupuestosAsociados.stream().filter(p -> p.isIndicadorDeAprobacion()).findAny().orElseThrow(NoHayPresupuestoElegidoException::new);
	}

	public boolean getIndicadorDeAprobacion(){
		return indicadorDeAprobacion;
	}

	public void agregarItem(Item item){
		this.items.add(item);
	}

	public void validarSuficientesPresupuestos(){
		if (cantidadMinimaDePresupuestos > presupuestosAsociados.size()){
			throw new NoHayPresupuestosSuficientesException();
		}
	}

	public Moneda getMoneda(){
		return this.moneda;
	}
}
