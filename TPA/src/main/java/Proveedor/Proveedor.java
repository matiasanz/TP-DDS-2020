package Proveedor;

public class Proveedor {
	private int dni;
	private int cuil;
	private String nombre;
	private String apellido;
	private String razonSocial;
	private Direccion direccion;
	
	public Proveedor(int dni, int cuil, String nombre, String apellido, String razonSocial, Direccion direccion) {
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.razonSocial = razonSocial;
		this.direccion = direccion;
	}

	//TODO FALTA EL FACTORY METHOD
}
