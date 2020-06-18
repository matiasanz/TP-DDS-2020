package Compra;
import Moneda.CodigoMoneda;
import Proveedor.Proveedor;
import Presupuesto.*;
import org.junit.Before;
import org.junit.Test;

import Entidad.EntidadBase;
import Entidad.EntidadJuridica;
import Entidad.OrganizacionSectorSocial;
import MedioDePago.PagoEnEfectivo;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestCompra {
    private ArrayList<EntidadBase> entidadesBase = new ArrayList<>();
    private EntidadJuridica entidad = new OrganizacionSectorSocial("Entidad de Prueba", "Entidad Real", "1222222224", "Avenida 123", 845, entidadesBase);
    private Proveedor proveedor = new Proveedor(22222222, 1222222224, "Juan", "Perez", "Razon Social", null);
    private PagoEnEfectivo medioDePago = new PagoEnEfectivo();
    private Compra compra;
    private Item item1 = new Item("Item 1", 1, BigDecimal.valueOf(50.0));
    private Item item2 = new Item("Item 1", 1, BigDecimal.valueOf(40.5));
    private Item item3 = new Item("Item 1", 1, BigDecimal.valueOf(9.5));

    @Before
    public void init() {
        compra = new Compra(new RepositorioDeMonedasMock(), entidad, proveedor,  LocalDate.now(), medioDePago, CodigoMoneda.ARS, 1, null);
        compra.agregarItem(item1);
        compra.agregarItem(item2);
        compra.agregarItem(item3);
    }

    @Test
    public void obtenerValorTotalDeUnaCompra() {
        assertEquals(compra.getValorTotal(), BigDecimal.valueOf(100.0));
    }
    
    @Test(expected = NoHayPresupuestosSuficientesException.class)
    public void fallaCantidadDePresupuestos() {
    	compra.validar();
    }
    
    @Test(expected = PresupuestoElegidoNoSeEncuentraEntreLosPresupuestosException.class)
    public void presupuestoElegidoNoSeEncuentraEntreLosPresupuestosDeLaCompra() {
    	//Agrego los presupuestos
    	List<Item> listaItems = new ArrayList<>();
    	listaItems.add(item1);
    	listaItems.add(item2);
    	listaItems.add(item3);
    	
    	Presupuesto presupuesto = new Presupuesto(listaItems, proveedor);
    	//Los agrego a la compra
    	compra.generarPresupuesto(presupuesto);
    	//Hago la validacion
    	compra.validar();
    }
    
    @Test(expected = PresupuestoElegidoNoCumpleCriterioException.class)
    public void presupuestoElegidoNoCumpleCriterio() {
    	//Agrego los presupuestos
    	List<Item> listaItems = new ArrayList<>();
    	listaItems.add(item1);
    	listaItems.add(item2);
    	listaItems.add(item3);
    	
    	Presupuesto presupuesto1 = new Presupuesto(listaItems, proveedor);
    	
    	listaItems.remove(item2);
    	
    	Presupuesto presupuesto2 = new Presupuesto(listaItems, proveedor);
    	//Los agrego a la compra
    	compra.generarPresupuesto(presupuesto1);
    	compra.generarPresupuesto(presupuesto2);
    	compra.elegirPresupuesto(presupuesto2);
    	//Hago la validacion
    	compra.validar();
    }
}
