package Entidad;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Factory.EntidadesFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class TestEntidad {
    private Entidad entidad;
    private LocalDate fechaInicioReporte;
    private String ETIQUETA_DEFECTO = "Sin Etiquetar";

    @Before
    public void init() {
        entidad = EntidadesFactory.getEntidadConCompras();
    }

    @Test()
    public void elReporteIncluyeComprasSiLaFechaEsIgual() {

        fechaInicioReporte = LocalDate.of(2017, 2, 28);
        Map<String, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertEquals(resultadoReporte.size(),1);
        Assert.assertEquals(resultadoReporte.get(ETIQUETA_DEFECTO), BigDecimal.valueOf(50).floatValue(),0);
    }

    @Test()
    public void elReporteIgnoraComprasSiLaFechaEsAnterior() {

        fechaInicioReporte = LocalDate.of(2016, 1, 31);
        Map<String, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertTrue(resultadoReporte.isEmpty());
    }

    @Test()
    public void elReporteCalculaElMontoTotalCorrectamenteParaUnaCompra() {

        fechaInicioReporte = LocalDate.of(2018, 3, 30);
        Map<String, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertEquals(1, resultadoReporte.size());
        Assert.assertEquals(BigDecimal.valueOf(59.5).floatValue(), resultadoReporte.get("Amoblamiento"),0);
    }

    @Test()
    public void elReporteAgrupaDistintasComprasSiLaEtiquetaCoincide() {

        fechaInicioReporte = LocalDate.of(2020, 7, 30);
        Map<String, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertEquals(3, resultadoReporte.size());
        Assert.assertEquals(BigDecimal.valueOf(9.5).floatValue(), resultadoReporte.get(ETIQUETA_DEFECTO),0);
        Assert.assertEquals(BigDecimal.valueOf(50).floatValue(), resultadoReporte.get("Amoblamiento"),0);
        Assert.assertEquals(BigDecimal.valueOf(69).floatValue(), resultadoReporte.get("Juan Perez"),0);
    }
}
