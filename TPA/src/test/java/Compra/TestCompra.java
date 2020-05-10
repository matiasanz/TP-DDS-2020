package Compra;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;

public class TestCompra {
    private Compra compra;

    @Before
    public void init() {
        compra = new Compra();

        compra.agregarItem(new Item("Item 1", BigDecimal.valueOf(50.0)));
        compra.agregarItem(new Item("Item 1", BigDecimal.valueOf(40.5)));
        compra.agregarItem(new Item("Item 1", BigDecimal.valueOf(9.5)));

    }

    @Test
    public void ObtenerValorTotalDeUnaCompra() {
        assertEquals(compra.getValorTotal(),BigDecimal.valueOf(100.0));
    }
}
