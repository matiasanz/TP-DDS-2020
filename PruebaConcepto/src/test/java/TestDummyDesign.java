import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestDummyDesign {

	@Test
	public void testIntegrante1(){
		assertEquals(1, DummyDesign.integrante1());
	}
	
    @Test
    public void testIntegrante3() {
        assertTrue(3 == DummyDesign.Integrante3());
    }

    @Test
    public void testIntegrante4() {
        assertEquals(4, DummyDesign.integrante4());
    }
    
    @Test
    public void testIntegrante5() {
        assertEquals(5, DummyDesign.integrante5());
    }
}