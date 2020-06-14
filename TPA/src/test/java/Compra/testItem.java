package Compra;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class testItem {
    @Test (expected = ValorNegativoEnItemDeCompraException.class)
    public void intentoCrearItemConValorNegativo() {
        Item item1 = new Item("Heladera", 3, BigDecimal.valueOf(-10));
    }

    @Test
    public void testeoQueElValorCalculadoEsElCorrecto(){
        Item item2 = new Item("Lavavajilla", 5, BigDecimal.valueOf(50300));
        Assert.assertEquals(BigDecimal.valueOf(251500), item2.getValor());
    }
}
