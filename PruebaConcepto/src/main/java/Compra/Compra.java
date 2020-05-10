package Compra;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import Entidad.EntidadJuridica;
import MedioDePago.MedioDePago;

public class Compra {
	private EntidadJuridica entidadRelacionada;
//	private Documento documentoComercial;
	private Proveedor proveedor;
	private LocalDate fechaOperacion;
	private BigDecimal valorTotal; 
	private ArrayList<Item> items;
	private MedioDePago medioDePago;
}
