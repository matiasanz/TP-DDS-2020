package Moneda;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Moneda {
    @Enumerated(EnumType.STRING)
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

