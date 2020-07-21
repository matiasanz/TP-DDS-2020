package Entidad;

import Direccion.Direccion;

import java.util.ArrayList;

public class Empresa extends EntidadJuridica {
	private Clasificacion clasificacion;
	
	public Empresa(String razonSocial, String nombreFicticio, String cuit, Direccion direccionPostal, int codigoIGJ,
				   ArrayList<EntidadBase> entidadesBase, Clasificacion clasificacion) {
		
		super(razonSocial, nombreFicticio, cuit, direccionPostal, codigoIGJ, entidadesBase);
		this.clasificacion = clasificacion;
	}

}
