package Repositorios;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import Compra.Compra;
import Factory.ComprasFactory;
import Factory.EntidadesFactory;
import Factory.EtiquetasFactory;

public class TestRepoCompras extends AbstractPersistenceTest implements WithGlobalEntityManager {

    RepoComprasDB compras = new RepoComprasDB();
    Compra compraJulioEtiqueta = ComprasFactory.getCompra19Julio2020Amoblamiento();
    Compra compraFebrero = ComprasFactory.getCompraFebrero2017SinEtiqueta();
    Compra compraJulioSinEtiqueta = getCompraJulioSinEtiquetas();

    LocalDate fechaJulio2020 = LocalDate.of(2020, 07, 1);

    @Before
    public void start() {
        compras.agregar(compraJulioEtiqueta);
        compras.agregar(compraFebrero);
    }

    @After
    public void end() {
        this.rollbackTransaction();
    }

    @Test
    public void compraSeRecuperaDeDB() {
        List<Compra> comprasRecuperadas = compras.getAll();

        assertEquals(2, comprasRecuperadas.size());
        assertCompra(compraJulioEtiqueta, comprasRecuperadas.get(0));
        assertCompra(compraFebrero, comprasRecuperadas.get(1));
    }

    @Test
    public void comprasDelMesDeJulio() {

        compraJulioSinEtiqueta.setFechaOperacion(LocalDate.of(2020, 7, 15));
        compras.agregar(compraJulioSinEtiqueta);
        List<Compra> comprasDelMes = compras.comprasDelMes(fechaJulio2020);
        assertEquals(2, comprasDelMes.size());
        assertCompra(compraJulioEtiqueta, comprasDelMes.get(0));
        assertCompra(compraJulioSinEtiqueta, comprasDelMes.get(1));
    }

    @Test
    public void etiquetasDelMes() {
        List<String> etiquetas = compras.repositorioDelMes(fechaJulio2020).getEtiquetas();
        assertEquals(1, etiquetas.size());
        assertEquals(EtiquetasFactory.etiquetaAmoblamiento(), etiquetas.get(0));
    }

    //Auxiliares *****************************

    private void assertCompra(Compra expected, Compra actual) {
        assertEquals(expected.getItems(), actual.getItems());
        assertEquals(expected.getValorTotal(), actual.getValorTotal());
        assertEquals(expected.getEtiquetas().size(), actual.getEtiquetas().size());
        assertEquals(expected.getPresupuestosAsociados().size(), actual.getPresupuestosAsociados().size());
    }

    private Compra getCompraJulioSinEtiquetas() {
        Compra compra = ComprasFactory.getCompraFebrero2017SinEtiqueta();
        compra.setEntidadRelacionada(EntidadesFactory.baseRandom());
        return compra;
    }
}

	