package Presupuesto;

import Compra.Item;
import Proveedor.Proveedor;

import java.math.BigDecimal;
import java.util.List;

public class Presupuesto implements Comparable<Presupuesto>{
    private List<Item> items;
    private Proveedor proveedorAsociado;

    public Presupuesto(List<Item> items, Proveedor proveedorAsociado) {
        this.items = items;
        this.proveedorAsociado = proveedorAsociado;
    }

    public BigDecimal getValorTotal() {
        return items.stream().map(Item::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int compareTo(Presupuesto otroPresupuesto) {
        return this.getValorTotal().compareTo(otroPresupuesto.getValorTotal());
    }
}
