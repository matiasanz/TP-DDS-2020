package Entidad;

import Direccion.Direccion;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "empresas")
public class Empresa extends EntidadJuridica {
	@Enumerated(EnumType.ORDINAL)
	private Clasificacion clasificacion;
	
	public Empresa() {
		
	}
	
	public Empresa(String razonSocial, String nombreFicticio, String cuit, Direccion direccionPostal, int codigoIGJ,
				   List<EntidadBase> entidadesBase, Clasificacion clasificacion) {
		
		super(razonSocial, nombreFicticio, cuit, direccionPostal, codigoIGJ, entidadesBase);
		this.clasificacion = clasificacion;
	}

}
