package Compra;
import Proveedor.Proveedor;
import Usuario.Usuario;
import Direccion.Direccion;
import Presupuesto.*;
import org.junit.Before;
import org.junit.Test;

import Entidad.EntidadBase;
import Entidad.EntidadJuridica;
import Entidad.OrganizacionSectorSocial;
import Factory.ComprasFactory;
import Factory.DireccionesFactory;
import Factory.ItemsFactory;
import Factory.UsuariosFactory;
import MedioDePago.PagoEnEfectivo;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
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
        proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", DireccionesFactory.direccionStub());
        medioDePago = new PagoEnEfectivo();
        item1 = ItemsFactory.itemValuadoEn(50.0);
        item2 = ItemsFactory.itemValuadoEn(40.5);
        item3 = ItemsFactory.itemValuadoEn(9.5);

        compra = ComprasFactory.compraSinEtiquetas(entidad,proveedor,medioDePago);
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
    	compra.agregarPresupuesto(presupuesto);
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

        List<Item> listaItems2 = new ArrayList<>();
        listaItems2.add(item2);
        Presupuesto presupuesto2 = new Presupuesto(listaItems2, proveedor);

    	//Los agrego a la compra
    	compra.agregarPresupuesto(presupuesto1);
    	compra.agregarPresupuesto(presupuesto2);
    	compra.setPresupuestoElegido(presupuesto1);
    	//Hago la validacion
    	compra.validar();
        assertEquals(Estado.RECHAZADA, compra.getIndicadorDeAprobacion());
    }
    
    @Test
    public void agregarUnaEtiquetaAunaCompraExistente() {
    	String etiqueta1 = "inmobilaria";
    	compra.agregarEtiqueta(etiqueta1);
        assertEquals(etiqueta1,compra.getEtiquetas().get(0));
    }
    
    @Test
    public void compraEsPersistible(){

    }
}
