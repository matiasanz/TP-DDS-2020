package Entidad;

import Compra.Item;
import Compra.Compra;
import Mocks.RepositorioDeMonedasMock;
import Etiqueta.Etiqueta;
import Mocks.RepositorioDeLocacionesMock;
import MedioDePago.PagoEnEfectivo;
import Moneda.CodigoMoneda;
import Direccion.Direccion;
import Direccion.Pais;
import Proveedor.Proveedor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import Etiqueta.EtiquetaPersonalizable;
import Etiqueta.EtiquetaProveedor;
import Etiqueta.EtiquetaDefecto;
import Presupuesto.Presupuesto;

public class TestEntidad {
    private ArrayList<EntidadBase> entidadesBase;
    private EntidadJuridica entidad;
    private Direccion direccion;
    private Proveedor proveedor;
    private PagoEnEfectivo medioDePago;
    private Compra compraEnero2020JuanPerez;
    private Compra compraFebrero2017SinEtiqueta;
    private Compra compraMarzo2018Amoblamiento;
    private Compra compraAbril2020Amoblamiento;
    private Etiqueta etiquetaAmoblamiento;
    private Etiqueta etiquetaProveedorJuanPerez;
    private Item item50;
    private Item item40Con50;
    private Item item9Con50;
    private LocalDate fechaInicioReporte;
    private Presupuesto presupuesto;

    @Before
    public void init() {

        item50 = new Item("Item 1", 1, BigDecimal.valueOf(50.0));
        item40Con50 = new Item("Item 1", 1, BigDecimal.valueOf(40.5));
        item9Con50 = new Item("Item 1", 1, BigDecimal.valueOf(9.5));

        List<Item> listaItems = new ArrayList<>();
        listaItems.add(item50);
        listaItems.add(item40Con50);
        listaItems.add(item9Con50);

        presupuesto = new Presupuesto(listaItems, proveedor);
        entidadesBase = new ArrayList<>();
        direccion = new Direccion(new RepositorioDeLocacionesMock(), "Cervantes", 607, 5, "1407", Pais.AR);

        entidad = new OrganizacionSectorSocial("Entidad de Prueba", "Entidad Real", "1222222224", direccion, 845, entidadesBase);
        direccion = new Direccion(new RepositorioDeLocacionesMock(), "Cervantes", 607, 5, "1407", Pais.AR);
        proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccion);

        etiquetaAmoblamiento = new EtiquetaPersonalizable( 1,"amoblamiento");
        etiquetaProveedorJuanPerez = new EtiquetaProveedor(2, proveedor);

        compraMarzo2018Amoblamiento = new Compra(new RepositorioDeMonedasMock(), entidad, proveedor, LocalDate.of(2018, 3, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, null);
        compraMarzo2018Amoblamiento.agregarItem(item50);
        compraMarzo2018Amoblamiento.agregarItem(item9Con50);
        compraMarzo2018Amoblamiento.setEtiqueta(etiquetaAmoblamiento);
        compraMarzo2018Amoblamiento.generarPresupuesto(presupuesto);
        compraMarzo2018Amoblamiento.setPresupuestoElegido(presupuesto);

        compraFebrero2017SinEtiqueta = new Compra(new RepositorioDeMonedasMock(), entidad, proveedor, LocalDate.of(2017, 2, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, null);
        compraFebrero2017SinEtiqueta.agregarItem(item50);
        compraFebrero2017SinEtiqueta.generarPresupuesto(presupuesto);
        compraFebrero2017SinEtiqueta.setPresupuestoElegido(presupuesto);

        compraEnero2020JuanPerez = new Compra(new RepositorioDeMonedasMock(), entidad, proveedor, LocalDate.of(2020, 1, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, null);
        compraEnero2020JuanPerez.agregarItem(item40Con50);
        compraEnero2020JuanPerez.agregarItem(item9Con50);
        compraEnero2020JuanPerez.agregarItem(item9Con50);
        compraEnero2020JuanPerez.agregarItem(item9Con50);
        compraEnero2020JuanPerez.setEtiqueta(etiquetaProveedorJuanPerez);
        compraEnero2020JuanPerez.generarPresupuesto(presupuesto);
        compraEnero2020JuanPerez.setPresupuestoElegido(presupuesto);

        compraAbril2020Amoblamiento = new Compra(new RepositorioDeMonedasMock(), entidad, proveedor, LocalDate.of(2020, 4, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, null);
        compraAbril2020Amoblamiento.agregarItem(item40Con50);
        compraAbril2020Amoblamiento.setEtiqueta(etiquetaAmoblamiento);
        compraAbril2020Amoblamiento.generarPresupuesto(presupuesto);
        compraAbril2020Amoblamiento.setPresupuestoElegido(presupuesto);

        entidad.agregarCompra(compraMarzo2018Amoblamiento);
        entidad.agregarCompra(compraFebrero2017SinEtiqueta);
        entidad.agregarCompra(compraEnero2020JuanPerez);
        entidad.agregarCompra(compraAbril2020Amoblamiento);
    }

    @Test()
    public void elReporteIncluyeComprasSiLaFechaEsIgual() {

        fechaInicioReporte = LocalDate.of(2017, 2, 1);
        Map<Etiqueta, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertEquals(resultadoReporte.size(),1);
        Assert.assertEquals(resultadoReporte.get(EtiquetaDefecto.getInstance()), BigDecimal.valueOf(50).floatValue(),0);
    }

    @Test()
    public void elReporteIgnoraComprasSiLaFechaEsAnterior() {

        fechaInicioReporte = LocalDate.of(2016, 1, 1);
        Map<Etiqueta, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertTrue(resultadoReporte.isEmpty());
    }

    @Test()
    public void elReporteCalculaElMontoTotalCorrectamenteParaUnaCompra() {

        fechaInicioReporte = LocalDate.of(2018, 4, 1);
        Map<Etiqueta, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertEquals(2, resultadoReporte.size());
        Assert.assertEquals(resultadoReporte.get(EtiquetaDefecto.getInstance()), BigDecimal.valueOf(50).floatValue(),0);
        Assert.assertEquals(resultadoReporte.get(etiquetaAmoblamiento), BigDecimal.valueOf(59.5).floatValue(),0);
    }

    @Test()
    public void elReporteAgrupaDistintasComprasSiLaEtiquetaCoincide() {

        fechaInicioReporte = LocalDate.of(2020, 5, 1);
        Map<Etiqueta, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertEquals(resultadoReporte.size(),3);
        Assert.assertEquals(resultadoReporte.get(EtiquetaDefecto.getInstance()), BigDecimal.valueOf(50).floatValue(),0);
        Assert.assertEquals(resultadoReporte.get(etiquetaAmoblamiento), BigDecimal.valueOf(100).floatValue(),0);
        Assert.assertEquals(resultadoReporte.get(etiquetaProveedorJuanPerez), BigDecimal.valueOf(69).floatValue(),0);
    }
}
