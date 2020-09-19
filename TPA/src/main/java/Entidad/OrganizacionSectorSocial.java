package Entidad;

import Direccion.Direccion;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class OrganizacionSectorSocial extends EntidadJuridica {

	public OrganizacionSectorSocial(String razonSocial, String nombreFicticio, String cuit, Direccion direccionPostal,
			int codigoIGJ, ArrayList<EntidadBase> entidadesBase) {
		super(razonSocial, nombreFicticio, cuit, direccionPostal, codigoIGJ, entidadesBase);
	}

}