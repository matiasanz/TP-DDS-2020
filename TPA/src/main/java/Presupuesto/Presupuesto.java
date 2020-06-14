package Presupuesto;

import Compra.Item;
import Proveedor.Proveedor;

import java.math.BigDecimal;
import java.util.List;

public class Presupuesto implements Comparable<Presupuesto>{
    private List<Item> items;
    private Proveedor proveedorAsociado;
    private boolean indicadorDeAprobacion;

    public Presupuesto(List<Item> items, Proveedor proveedorAsociado) {
        this.items = items;
        this.proveedorAsociado = proveedorAsociado;
        this.indicadorDeAprobacion = false;
    }

    public boolean isIndicadorDeAprobacion() {
        return indicadorDeAprobacion;
    }

    public void setIndicadorDeAprobacion(boolean indicadorDeAprobacion) {
        this.indicadorDeAprobacion = indicadorDeAprobacion;
    }

    public BigDecimal getValorTotal() {
        return items.stream().map(Item::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int compareTo(Presupuesto otroPresupuesto) {
        return this.getValorTotal().compareTo(otroPresupuesto.getValorTotal());
    }
}
