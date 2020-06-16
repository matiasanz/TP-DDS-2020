package Entidad;

public class EntidadBase implements Entidad {
	private String nombreFicticio;
	private String descripcion;
	public void imprimirDatos(){
		System.out.println(">> Nombre ficticio: " + nombreFicticio);
//		System.out.println(">> Descripcion: " + descripcion);
	}
}
