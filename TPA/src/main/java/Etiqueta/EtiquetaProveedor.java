package Etiqueta;

import Proveedor.Proveedor;

public class EtiquetaProveedor implements Etiqueta {

    private Proveedor proveedor;

    public EtiquetaProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String getNombre() {
        return this.proveedor.getNombre();
    }

}
