package Entidad;
import java.util.ArrayList;

public abstract class EntidadJuridica implements Entidad {
	private String razonSocial;
	private String nombreFicticio;
	private String cuit;
	private String direccionPostal;
	private int codigoIGJ;
	private ArrayList<EntidadBase> entidadesBase;
	
	public EntidadJuridica(String razonSocial, String nombreFicticio, String cuit, String direccionPostal, int codigoIGJ,
			ArrayList<EntidadBase> entidadesBase) {
		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codigoIGJ = codigoIGJ;
		this.entidadesBase = entidadesBase;
	}
}
