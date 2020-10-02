package Entidad;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "entidades_base")
public class EntidadBase extends Entidad {
	private String descripcion;
	
	public EntidadBase() {
		
	}

	public EntidadBase(String nombreFicticio, String descripcion){
		super(nombreFicticio);
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
