package Compra;
import Mocks.RepositorioDeLocacionesMock;
import Mocks.RepositorioDeMonedasMock;
import Moneda.CodigoMoneda;
import Proveedor.Proveedor;
import Direccion.Pais;
import Direccion.Direccion;
import Presupuesto.*;
import Repositorios.RepositorioDeEtiquetas.RepositorioEtiquetas;
import org.junit.Before;
import org.junit.Test;

import Entidad.EntidadBase;
import Entidad.EntidadJuridica;
import Entidad.OrganizacionSectorSocial;
import Etiqueta.Etiqueta;
import Etiqueta.EtiquetaPersonalizable;
import Factory.ComprasFactory;
import Factory.VariosFactory;
import MedioDePago.PagoEnEfectivo;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
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
        proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", VariosFactory.direccionStub());
        medioDePago = new PagoEnEfectivo();
        item1 = ComprasFactory.itemValuadoEn(50.0);
        item2 = ComprasFactory.itemValuadoEn(40.5);
        item3 = ComprasFactory.itemValuadoEn(9.5);

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
    
    @Test
    public void agregarUnaEtiquetaAunaCompraExistente() {
    	Etiqueta etiqueta1 = new EtiquetaPersonalizable("inmobilaria");
    	compra.setEtiqueta(etiqueta1);
        assertEquals(etiqueta1,compra.getEtiqueta());
    }
}
