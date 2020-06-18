package Presupuesto;
import Proveedor.*;
import Presupuesto.*;
import Compra.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPresupuesto {
    private Proveedor proveedor = new Proveedor(22222222, 1222222224, "Juan", "Perez", "Razon Social", null);
    private Item item1 = new Item("Item 1", 1, BigDecimal.valueOf(50.0));
    private Item item2 = new Item("Item 1", 1, BigDecimal.valueOf(40.5));
    private Item item3 = new Item("Item 1", 1, BigDecimal.valueOf(9.5));
    private Presupuesto presupuesto;
    
    @Before
    public void init() {
    	List<Item> listaItems = new ArrayList<>();
    	listaItems.add(item1);
    	listaItems.add(item2);
    	listaItems.add(item3);
    	
    	presupuesto = new Presupuesto(listaItems, proveedor);
    }

    @Test
    public void obtenerValorTotalDeUnPresupuesto() {
        Assert.assertEquals(presupuesto.getValorTotal(), BigDecimal.valueOf(100.0));
    }
    
    @Test
    public void compararElPresupuestoConOtroIgual() {
    	//Genero otro presupuesto
    	Item itemAlternativo1 = new Item("Item 1", 1, BigDecimal.valueOf(50.0));
        Item itemAlternativo2 = new Item("Item 1", 1, BigDecimal.valueOf(50.0));
        List<Item> listaItemsAlternativos = new ArrayList<>();
        listaItemsAlternativos.add(itemAlternativo1);
        listaItemsAlternativos.add(itemAlternativo2);
        Presupuesto presupuestoAlternativo = new Presupuesto(listaItemsAlternativos, proveedor);
        //Realizo la comparacion
        Assert.assertEquals(0, presupuesto.compareTo(presupuestoAlternativo));
    }
    
    @Test
    public void compararElPresupuestoConOtroDistinto() {
    	//Genero otro presupuesto
    	Item itemAlternativo1 = new Item("Item 1", 1, BigDecimal.valueOf(350.0));
        Item itemAlternativo2 = new Item("Item 1", 1, BigDecimal.valueOf(520.0));
        List<Item> listaItemsAlternativos = new ArrayList<>();
        listaItemsAlternativos.add(itemAlternativo1);
        listaItemsAlternativos.add(itemAlternativo2);
        Presupuesto presupuestoAlternativo = new Presupuesto(listaItemsAlternativos, proveedor);
        //Realizo la comparacion
        Assert.assertTrue(presupuesto.compareTo(presupuestoAlternativo) == 1 || presupuesto.compareTo(presupuestoAlternativo) == -1);
    }
}
