package Etiqueta;

import Proveedor.Proveedor;

public class EtiquetaProveedor implements Etiqueta {

    private Integer identificador;
    private Proveedor proveedor;

    public EtiquetaProveedor(Integer identificador, Proveedor proveedor) {
        this.identificador = identificador;
        this.proveedor = proveedor;
    }

    @Override
    public String getNombre() {
        return this.proveedor.getNombre();
    }

    @Override
    public Integer getIdentificador() {
        return this.identificador;
    }
}
