package Modelos;

import java.math.BigDecimal;
import java.util.List;

import Compra.Compra;
import Compra.Estado;
import Compra.Item;

public class CompraModel {
	private Long id;
	private String fecha;
	private String medioDePago;
	private String moneda;
	private BigDecimal precio;
	private List<Item> items;
	private List<String> etiquetas;
	private String estadoDeAprobacion;
	
	public CompraModel(Compra compra) {
		this.id = compra.getId();
		this.fecha = compra.getFechaOperacion().getDayOfMonth() + "/" + compra.getFechaOperacion().getMonthValue() + "/" + compra.getFechaOperacion().getYear();
		this.medioDePago = compra.getMedioDePago().getDescripcion();
		this.moneda = compra.getMoneda().getDescripcion();
		this.precio = compra.getValorTotal();
		this.items = compra.getItems();
		this.etiquetas = compra.getEtiquetas();
		this.estadoDeAprobacion=compra.getIndicadorDeAprobacion().toString();
	}

	public Long getId() {
		return id;
	}

	public String getFecha() {
		return fecha;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public String getMoneda() {
		return moneda;
	}
	
	public String getEstadoDeAprobacion() {
		return estadoDeAprobacion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public List<Item> getItems() {
		return items;
	}

	public List<String> getEtiquetas() {
		return etiquetas;
	}
	
}