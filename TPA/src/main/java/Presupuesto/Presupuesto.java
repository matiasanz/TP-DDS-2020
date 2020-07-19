package Presupuesto;

import Compra.Item;
import Proveedor.Proveedor;

import java.math.BigDecimal;
import java.util.List;

public class Presupuesto implements Comparable<Presupuesto>{
    private List<Item> items;
    private Proveedor proveedorAsociado;
    private boolean elegido;

    public Presupuesto(List<Item> items, Proveedor proveedorAsociado) {
        this.elegido = false;
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

    public boolean isElegido() {
        return elegido;
    }

    public void setElegido(boolean elegido) {
        this.elegido = elegido;
    }
}
