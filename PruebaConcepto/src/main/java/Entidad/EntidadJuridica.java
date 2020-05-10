package Entidad;
import java.util.List;

public abstract class EntidadJuridica implements Entidad {
	private String razonSocial;
	private String nombreFicticio;
	private int cuit;
	private String direccionPostal;
	private int codigoIGJ;
	private List<EntidadBase> entidadesBase;
	
}
