package Compra;

public class Proveedor {
	private int dni;
	private int cuil;
	private String nombre;
	private String apellido;
	private String razonSocial;
	private String direccionPostal;
	
	public Proveedor(int dni, int cuil, String nombre, String apellido, String razonSocial, String direccionPostal) {
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.razonSocial = razonSocial;
		this.direccionPostal = direccionPostal;
	}
}
