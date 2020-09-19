package Entidad;
import Direccion.Direccion;

import java.util.List;

public abstract class EntidadJuridica extends Entidad {
	private String razonSocial;
	private String nombreFicticio;
	private String cuit;
	private Direccion direccionPostal;
	private int codigoIGJ;
	private List<EntidadBase> entidadesBase;
	
	public EntidadJuridica(String razonSocial, String nombreFicticio, String cuit, Direccion direccionPostal, int codigoIGJ,
			List<EntidadBase> entidadesBase) {
		super();
		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codigoIGJ = codigoIGJ;
		this.entidadesBase = entidadesBase;
	}

	public void agregarEntidadBase(EntidadBase entidad){
		getCategorias().forEach(categoria -> categoria.notificarEntidadBaseAgregada());
		entidad.getCategorias().forEach(categoria -> categoria.notificarMeAgregueAUnaJuridica(this));
		entidadesBase.add(entidad);
	}

}
