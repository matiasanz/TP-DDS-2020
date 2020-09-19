package Entidad;

import javax.persistence.Entity;

@Entity
public class EntidadBase extends Entidad {
	private String descripcion;

	public EntidadBase(String nombreFicticio, String descripcion){
		super();
		this.nombreFicticio = nombreFicticio;
		this.descripcion = descripcion;
	}
}
