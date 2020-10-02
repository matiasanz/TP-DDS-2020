package Presupuesto;

import Compra.Compra;
import Compra.Item;
import Proveedor.Proveedor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "presupuestos")
public class Presupuesto implements Comparable<Presupuesto>{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name = "items_por_presupuesto")
    private List<Item> items;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedorAsociado;

    private boolean elegido;
    
    public Presupuesto() {
    	
    }

    public Presupuesto(List<Item> items, Proveedor proveedorAsociado) {
        this.elegido = false;
        this.items = items;
        this.proveedorAsociado = proveedorAsociado;
    }

    public List<Item> getItems() {
        return items;
    }

    public Proveedor getProveedorAsociado() {
        return proveedorAsociado;
    }

    public BigDecimal getValorTotal() {
        if(this.items != null) {
            return items.stream().map(Item::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return new BigDecimal(0);
    }

    @Override
    public int compareTo(Presupuesto otroPresupuesto) {
        return this.getValorTotal().compareTo(otroPresupuesto.getValorTotal());
    }

    public boolean equals(Presupuesto otro){
    	return Objects.equals(getValorTotal(), otro.getValorTotal());
    }
    
    public boolean isElegido() {
        return elegido;
    }

    public void setElegido(boolean elegido) {
        this.elegido = elegido;
    }

	public Long getId()
	{
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setProveedorAsociado(Proveedor proveedorAsociado) {
		this.proveedorAsociado = proveedorAsociado;
	}
	
}
