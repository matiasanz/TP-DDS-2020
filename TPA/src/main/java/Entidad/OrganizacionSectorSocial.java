package Entidad;

import Direccion.Direccion;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "organizaciones_sector_social")
public class OrganizacionSectorSocial extends EntidadJuridica {
	
	public OrganizacionSectorSocial() {
		
	}

	public OrganizacionSectorSocial(String razonSocial, String nombreFicticio, String cuit, Direccion direccionPostal,
			int codigoIGJ, ArrayList<EntidadBase> entidadesBase) {
		super(razonSocial, nombreFicticio, cuit, direccionPostal, codigoIGJ, entidadesBase);
	}

}