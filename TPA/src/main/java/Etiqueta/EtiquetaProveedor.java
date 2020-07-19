package Etiqueta;

import Proveedor.Proveedor;

public class EtiquetaProveedor implements Etiqueta{

    private Proveedor proveedor;

    public EtiquetaProveedor(Proveedor proveedor){
        this.proveedor = proveedor;
    }

    @Override
    public boolean cumpleCriterio(String stringComparador) {
        return proveedor.getNombre().equalsIgnoreCase(stringComparador);
    }
}
