package Moneda;

public class Moneda {
    private CodigoMoneda codigo;
    private final String descripcion;

    public Moneda(CodigoMoneda codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public CodigoMoneda getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

