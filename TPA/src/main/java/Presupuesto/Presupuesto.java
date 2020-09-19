package Presupuesto;

import Compra.Item;
import Proveedor.Proveedor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Presupuesto implements Comparable<Presupuesto>{

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    @JoinTable(name = "items_por_presupuesto")
    private List<Item> items;

    @Transient
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
