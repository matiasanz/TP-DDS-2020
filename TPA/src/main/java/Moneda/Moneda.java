package Moneda;

import org.apache.commons.lang3.EnumUtils;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Embeddable
public class Moneda {
    @Enumerated(EnumType.STRING)
    private CodigoMoneda codigo;

    private /*final*/ String descripcion;

    public Moneda() {
    }

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

    public static List<CodigoMoneda> codigosMoneda() {
        return EnumUtils.getEnumList(CodigoMoneda.class);
    }
}

