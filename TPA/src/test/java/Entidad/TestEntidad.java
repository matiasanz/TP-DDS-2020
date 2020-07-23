package Entidad;
import Mocks.RepositorioDeMonedasMock;
import Repositorios.RepositorioDeEntidades.RepositorioDeEntidades;
import Repositorios.RepositorioDeEtiquetas.RepositorioEtiquetas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class TestEntidad {
    private Entidad entidad;
    private LocalDate fechaInicioReporte;
    private RepositorioEtiquetas repositorioEtiquetas;

    @Before
    public void init() {
        repositorioEtiquetas = new RepositorioEtiquetas();
        entidad = new RepositorioDeEntidades(new RepositorioDeMonedasMock()).getEntidadConCompras();
    }

    @Test()
    public void elReporteIncluyeComprasSiLaFechaEsIgual() {

        fechaInicioReporte = LocalDate.of(2017, 2, 28);
        Map<String, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertEquals(resultadoReporte.size(),1);
        Assert.assertEquals(resultadoReporte.get(repositorioEtiquetas.getEtiquetaDefecto().getNombre()), BigDecimal.valueOf(50).floatValue(),0);
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
        Assert.assertEquals(resultadoReporte.get(repositorioEtiquetas.getEtiquetaAmoblamiento().getNombre()), BigDecimal.valueOf(59.5).floatValue(),0);
    }

    @Test()
    public void elReporteAgrupaDistintasComprasSiLaEtiquetaCoincide() {

        fechaInicioReporte = LocalDate.of(2020, 7, 30);
        Map<String, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaInicioReporte);
        Assert.assertEquals(3, resultadoReporte.size());
        Assert.assertEquals(resultadoReporte.get(repositorioEtiquetas.getEtiquetaDefecto().getNombre()), BigDecimal.valueOf(9.5).floatValue(),0);
        Assert.assertEquals(resultadoReporte.get(repositorioEtiquetas.getEtiquetaAmoblamiento().getNombre()), BigDecimal.valueOf(50).floatValue(),0);
        Assert.assertEquals(resultadoReporte.get(repositorioEtiquetas.getEtiquetaProveedorJuanPerez().getNombre()), BigDecimal.valueOf(69).floatValue(),0);
    }
}
