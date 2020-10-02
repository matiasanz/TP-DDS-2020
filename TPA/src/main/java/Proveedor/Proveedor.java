package Proveedor;

import Direccion.Direccion;

import javax.persistence.*;

@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue
    private Long id;

    private int dni;

    private int cuil;

    private String nombre;

    private String apellido;

    @Column(name = "razon_social")
    private String razonSocial;

    @Embedded
    private Direccion direccion;
    
    public Proveedor() {
    	
    }
    
    public static Proveedor PersonaFisica(int dni, int cuil, String nombre, String apellido, Direccion direccion) {
        return new Proveedor(dni, cuil, nombre, apellido, direccion);
    }

    public static Proveedor PersonaJuridica(String razonSocial, Direccion direccion) {
        return new Proveedor(razonSocial, direccion);
    }

    private Proveedor(int dni, int cuil, String nombre, String apellido, Direccion direccion) {
        this.dni = dni;
        this.cuil = cuil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    private Proveedor(String razonSocial, Direccion direccion) {
        this.razonSocial = razonSocial;
        this.direccion = direccion;
    }

    public String getNombre(){
        if(dni != 0){
            return nombre + " " + apellido;
        }
        else{
            return razonSocial;
        }
    }

	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getCuil() {
		return cuil;
	}

	public void setCuil(int cuil) {
		this.cuil = cuil;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	

}
