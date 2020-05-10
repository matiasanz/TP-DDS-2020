package Usuario;
import org.junit.Test;

public class TestUsuario {

    @Test (expected = contraseniaEntreLasDiezMilException.class)
    public void contraseniaEntreLas10000peores()  { 
    	Usuario usuario = new Usuario("guido","qwerty");
    }
}
