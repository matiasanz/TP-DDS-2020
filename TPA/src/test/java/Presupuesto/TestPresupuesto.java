package Presupuesto;
import Direccion.Direccion;
import Direccion.Pais;
import Factory.ComprasFactory;
import Factory.ItemsFactory;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMock;
import Proveedor.*;
import Compra.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPresupuesto {
    private Proveedor proveedor;
    private Item item1;
    private Item item2;
    private Item item3;
    private Direccion direccion;
    private Presupuesto presupuesto;
    
    @Before
    public void init() {
        direccion = new Direccion(new RepositorioDeLocacionesMock(), "Cervantes", 607, 5, "1407", Pais.AR);
        proveedor = Proveedor.PersonaJuridica("Arcos Dorados S.A.", direccion);
        item1 = ItemsFactory.itemValuadoEn(50.0);
        item2 = ItemsFactory.itemValuadoEn(40.5);
        item3 = ItemsFactory.itemValuadoEn(9.5);
    	List<Item> listaItems = new ArrayList<>();
    	listaItems.add(item1);
    	listaItems.add(item2);
    	listaItems.add(item3);
    	
    	presupuesto = new Presupuesto(listaItems, proveedor);
    }

    @Test
    public void obtenerValorTotalDeUnPresupuesto() {
        Assert.assertEquals(BigDecimal.valueOf(100.0), presupuesto.getValorTotal());
    }
    
    @Test
    public void compararElPresupuestoConOtroIgual() {
    	//Genero otro presupuesto
    	Item itemAlternativo1 = ItemsFactory.itemValuadoEn(50.0);
        Item itemAlternativo2 = ItemsFactory.itemValuadoEn(50.0);
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
    	Item itemAlternativo1 = ItemsFactory.itemValuadoEn(350.0);
        Item itemAlternativo2 = ItemsFactory.itemValuadoEn(520.0);
        List<Item> listaItemsAlternativos = new ArrayList<>();
        listaItemsAlternativos.add(itemAlternativo1);
        listaItemsAlternativos.add(itemAlternativo2);
        Presupuesto presupuestoAlternativo = new Presupuesto(listaItemsAlternativos, proveedor);
        //Realizo la comparacion
        Assert.assertTrue(presupuesto.compareTo(presupuestoAlternativo) == 1 || presupuesto.compareTo(presupuestoAlternativo) == -1);
    }
}
