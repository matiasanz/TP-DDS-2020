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
import Repositorios.RepositorioDeCompras.RepositorioDeCompras;
import Repositorios.RepositorioDeEntidades.RepositorioDeEntidades;
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
    private Direccion direccion;
    private Proveedor proveedor;
    private Entidad entidad;
    private LocalDate fechaInicioReporte;
    private RepositorioDeCompras repositorioDeCompras;

    @Before
    public void init() {
        repositorioDeCompras = new RepositorioDeCompras();
        direccion = new Direccion(new RepositorioDeLocacionesMock(), "Cervantes", 607, 5, "1407", Pais.AR);
        proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccion);
        entidad = new RepositorioDeEntidades().getEntidadConCompras();
    }

    @Test()
    public void elReporteIncluyeComprasSiLaFechaEsIgual() {

        fechaInicioReporte = LocalDate.of(2017, 2, 28);
        Map<Integer, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertEquals(resultadoReporte.size(),1);
        Assert.assertEquals(resultadoReporte.get(0), BigDecimal.valueOf(50).floatValue(),0);
    }

    @Test()
    public void elReporteIgnoraComprasSiLaFechaEsAnterior() {

        fechaInicioReporte = LocalDate.of(2016, 1, 31);
        Map<Integer, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertTrue(resultadoReporte.isEmpty());
    }

    @Test()
    public void elReporteCalculaElMontoTotalCorrectamenteParaUnaCompra() {

        fechaInicioReporte = LocalDate.of(2018, 3, 30);
        Map<Integer, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertEquals(1, resultadoReporte.size());
        Assert.assertEquals(resultadoReporte.get(1), BigDecimal.valueOf(59.5).floatValue(),0);
    }

    @Test()
    public void elReporteAgrupaDistintasComprasSiLaEtiquetaCoincide() {

        fechaInicioReporte = LocalDate.of(2020, 7, 30);
        Map<Integer, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertEquals(2, resultadoReporte.size());
        Assert.assertEquals(resultadoReporte.get(1), BigDecimal.valueOf(50).floatValue(),0);
        Assert.assertEquals(resultadoReporte.get(2), BigDecimal.valueOf(69).floatValue(),0);
    }
}
