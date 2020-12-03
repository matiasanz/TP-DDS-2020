package Entidad;
import Factory.ComprasFactory;
import org.junit.Assert;
import org.junit.Test;

import Factory.EtiquetasFactory;
import TareasProgramadas.ReporteMensualGastos.ReporteMensualDeGastos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class TestReporteDeGastos {
    private LocalDate fechaInicioReporte;
    private String ETIQUETA_DEFECTO = "Sin Etiquetar";
    private String ETIQUETA_AMOBLAMIENTO = EtiquetasFactory.etiquetaAmoblamiento();

    @Test()
    public void elReporteIncluyeComprasSiLaFechaEsIgual() {

        fechaInicioReporte = LocalDate.of(2017, 2, 28);
        Map<String, Double> resultadoReporte = ReporteMensualDeGastos.generarReporteDeGastos(ComprasFactory.getComprasConPresupuestoElegido(), fechaInicioReporte);
        Assert.assertEquals(1, resultadoReporte.size());
        Assert.assertEquals(resultadoReporte.get(ETIQUETA_DEFECTO), BigDecimal.valueOf(50).floatValue(),0);
    }

    @Test()
    public void elReporteIgnoraComprasSiLaFechaEsAnterior() {

        fechaInicioReporte = LocalDate.of(2016, 1, 31);
        Map<String, Double> resultadoReporte = ReporteMensualDeGastos.generarReporteDeGastos(ComprasFactory.getComprasConPresupuestoElegido(), fechaInicioReporte);
        Assert.assertTrue(resultadoReporte.isEmpty());
    }

    @Test()
    public void elReporteCalculaElMontoTotalCorrectamenteParaUnaCompra() {

        fechaInicioReporte = LocalDate.of(2018, 3, 30);
        Map<String, Double> resultadoReporte = ReporteMensualDeGastos.generarReporteDeGastos(ComprasFactory.getComprasConPresupuestoElegido(), fechaInicioReporte);
        Assert.assertEquals(1, resultadoReporte.size());
        Assert.assertEquals(BigDecimal.valueOf(59.5).floatValue(), resultadoReporte.get(ETIQUETA_AMOBLAMIENTO),0);
    }

    @Test()
    public void elReporteAgrupaDistintasComprasSiLaEtiquetaCoincide() {

        fechaInicioReporte = LocalDate.of(2020, 7, 30);
        Map<String, Double> resultadoReporte = ReporteMensualDeGastos.generarReporteDeGastos(ComprasFactory.getComprasConPresupuestoElegido(), fechaInicioReporte);
        Assert.assertEquals(3, resultadoReporte.size());
        Assert.assertEquals(BigDecimal.valueOf(9.5).floatValue(), resultadoReporte.get(ETIQUETA_DEFECTO),0);
        Assert.assertEquals(BigDecimal.valueOf(50).floatValue(), resultadoReporte.get(ETIQUETA_AMOBLAMIENTO),0);
        Assert.assertEquals(BigDecimal.valueOf(69).floatValue(), resultadoReporte.get("Juan Perez"),0);
    }
}
