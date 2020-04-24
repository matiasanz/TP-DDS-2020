import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestDummyDesign {

    @Test
    public void testIntegrante3() {
        assertTrue(3 == DummyDesign.Integrante3());
    }

    @Test
    public void testIntegrante4() {
        assertEquals(4, DummyDesign.integrante4());
    }
}