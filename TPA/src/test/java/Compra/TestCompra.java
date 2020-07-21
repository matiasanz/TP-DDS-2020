package Compra;
import Mocks.RepositorioDeLocacionesMock;
import Mocks.RepositorioDeMonedasMock;
import Moneda.CodigoMoneda;
import Proveedor.Proveedor;
import Direccion.Pais;
import Direccion.Direccion;
import Presupuesto.*;
import org.junit.Before;
import org.junit.Ignore;
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
    private ArrayList<EntidadBase> entidadesBase;
    private EntidadJuridica entidad;
    private Direccion direccion;
    private Proveedor proveedor;
    private PagoEnEfectivo medioDePago;
    private Compra compra;
    private Item item1;
    private Item item2;
    private Item item3;

    @Before
    public void init() {
        entidadesBase = new ArrayList<>();
        entidad = new OrganizacionSectorSocial("Entidad de Prueba", "Entidad Real", "1222222224", direccion, 845, entidadesBase);
        direccion = new Direccion(new RepositorioDeLocacionesMock(), "Cervantes", 607, 5, "1407", Pais.AR);
        proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccion);
        medioDePago = new PagoEnEfectivo();
        item1 = new Item("Item 1", 1, BigDecimal.valueOf(50.0));
        item2 = new Item("Item 1", 1, BigDecimal.valueOf(40.5));
        item3 = new Item("Item 1", 1, BigDecimal.valueOf(9.5));

        compra = new Compra(new RepositorioDeMonedasMock(), entidad, proveedor,  LocalDate.now(), medioDePago, CodigoMoneda.ARS, 1, null);
        compra.agregarItem(item1);
        compra.agregarItem(item2);
        compra.agregarItem(item3);
    }

    @Test
    public void obtenerValorTotalDeUnaCompra() {
        assertEquals(compra.getValorTotal(), BigDecimal.valueOf(100.0));
    }

    @Test
    public void fallaCantidadDePresupuestos() {
    	compra.validar();
        assertEquals(compra.getIndicadorDeAprobacion(), Estado.RECHAZADA);
    }

    @Test
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
        assertEquals(compra.getIndicadorDeAprobacion(), Estado.RECHAZADA);
    }

    @Test
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
    	compra.setPresupuestoElegido(presupuesto2);
    	//Hago la validacion
    	compra.validar();
        assertEquals(compra.getIndicadorDeAprobacion(), Estado.RECHAZADA);
    }
}
