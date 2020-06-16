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
	
	public static void datos(){
		System.out.println("Razon social | Nombre Ficticio | CUIT | Direccion Postal | Código IGJ");
	}
	
	public String toString(){
		return String.join("\n"," >> Razon Social: "+razonSocial,
								" >> NombreFicticio: "+nombreFicticio,
								" >> CUIT: "+cuit,
								" >> Direccion Postal: " + direccionPostal,
								" >> Codigo IGJ: " + Integer.toString(codigoIGJ));
	}
	
	public void imprimirDatos(){
		System.out.println(this.toString());
	}
}
