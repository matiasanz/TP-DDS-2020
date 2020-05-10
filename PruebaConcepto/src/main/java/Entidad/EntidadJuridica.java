package Entidad;
import java.util.ArrayList;

public abstract class EntidadJuridica implements Entidad {
	private String razonSocial;
	private String nombreFicticio;
	private int cuit;
	private String direccionPostal;
	private int codigoIGJ;
	private ArrayList<EntidadBase> entidadesBase;
	
}
