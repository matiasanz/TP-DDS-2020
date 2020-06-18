package Proveedor;

public class Proveedor {
    private int dni;
    private int cuil;
    private String nombre;
    private String apellido;
    private String razonSocial;
    private Direccion direccion;

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
}
