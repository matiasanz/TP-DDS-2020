package Entidad;
import Repositorios.RepositorioDeEntidades.RepositorioDeEntidades;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class TestEntidad {
    private Entidad entidad;
    private LocalDate fechaInicioReporte;

    @Before
    public void init() {
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
        Assert.assertEquals(3, resultadoReporte.size());
        Assert.assertEquals(resultadoReporte.get(0), BigDecimal.valueOf(9.5).floatValue(),0);
        Assert.assertEquals(resultadoReporte.get(1), BigDecimal.valueOf(50).floatValue(),0);
        Assert.assertEquals(resultadoReporte.get(2), BigDecimal.valueOf(69).floatValue(),0);
    }
}
