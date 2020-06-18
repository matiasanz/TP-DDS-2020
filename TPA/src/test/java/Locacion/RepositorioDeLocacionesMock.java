package Locacion;

import Proveedor.Pais;
import Repositorios.RepositorioDeLocaciones.*;

public class RepositorioDeLocacionesMock implements RepositorioDeLocaciones {
	String estado = "";
		
	@Override
	public Locacion getLocacion(Pais codigoPais, String codigoPostal) {
		if(estado.isEmpty()) return null;
		else return new Locacion(Pais.AR, "CABA", "Caballito");
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
}