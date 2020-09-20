package Entidad;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "entidades_base")
public class EntidadBase extends Entidad {
	private String descripcion;

	public EntidadBase(String nombreFicticio, String descripcion){
		super(nombreFicticio);
		this.descripcion = descripcion;
	}
}
