package Entidad;
import Categoria.BloqueoDeAgregarEntidadesBaseException;
import Categoria.EntidadJuridicaBloqueadaException;

import java.util.List;

public abstract class EntidadJuridica extends Entidad {
	private String razonSocial;
	private String nombreFicticio;
	private String cuit;
	private String direccionPostal;
	private int codigoIGJ;
	private List<EntidadBase> entidadesBase;
	
	public EntidadJuridica(String razonSocial, String nombreFicticio, String cuit, String direccionPostal, int codigoIGJ,
			List<EntidadBase> entidadesBase) {
		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codigoIGJ = codigoIGJ;
		this.entidadesBase = entidadesBase;
	}

	void agregarEntidadBase(EntidadBase entidad){
		try {
			getCategorias().forEach(categoria -> categoria.notificarEntidadBaseAgregada());
			entidad.getCategorias().forEach(categoria -> categoria.notificarMeAgregueAUnaJuridica(this));
			entidadesBase.add(entidad);
		} catch (BloqueoDeAgregarEntidadesBaseException | EntidadJuridicaBloqueadaException e){

		}
	}

}
