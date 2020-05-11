package Compra;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import Entidad.EntidadJuridica;
import MedioDePago.MedioDePago;

public class Compra {
	private EntidadJuridica entidadRelacionada;
//	private Documento documentoComercial;
	private Proveedor proveedor;
	private LocalDate fechaOperacion;
	private BigDecimal valorTotal; 
	private List<Item> items = new ArrayList<>();
	private MedioDePago medioDePago;
	
	public BigDecimal getValorTotal() {
		return items.stream().map(Item::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public void agregarItem(Item item){
		this.items.add(item);
	}
}
