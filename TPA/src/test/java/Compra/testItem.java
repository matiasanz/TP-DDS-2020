package Compra;

import java.math.BigDecimal;
import org.junit.Test;

public class testItem {
    @Test (expected = ValorNegativoEnItemDeCompraException.class)
    public void intentoCrearItemConValorNegativo() {
        Item item1 = new Item("Heladera", BigDecimal.valueOf(-10));
    }
}
