package Usuario;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestUsuario {


    @Test(expected = ContraseniaEntreLasDiezMilException.class)
    public void contraseniaEntreLas10000peores() {

        Usuario usuario = new Usuario("guido", "qwerty");
    }

    @Test(expected = ContraseniaNoCumpleLongitudMinimaException.class)
    public void contraseniaNoCumpleConLongitudMinima() {
        Usuario usuario = new Usuario("carlitos", "pepe");
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidos.class)
    public void validarCaracteresRepetidosConTresCaracteres() {
        Usuario usuario = new Usuario("carlitos", "pepeholappp");
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidos.class)
    public void validarCaracteresRepetidosConCuatroCaracteres() {
        Usuario usuario = new Usuario("carlitos", "pepeholappppp");
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidos.class)
    public void validarCaracteresRepetidosConDosCaracteres() {
        String contraseniaEsperada = "pepeholapp";
        Usuario usuario = new Usuario("carlitos", contraseniaEsperada);
    }
    
    @Test(expected = ContraseniaTieneNombreDeUsuarioIncluido.class)
    public void validarCaracteresQueIncluyenElNombreDeUsuario() {
        String contraseniaEsperada = "elmascrackesCarlitos";
        Usuario usuario = new Usuario("carlitos", contraseniaEsperada);
    }

    @Test()
    public void validarContraseniaUsandoContraseniaValida() {
        String contraseniaEsperada = "ASDhdursososososksms";
        Usuario usuario = new Usuario("carlitos", contraseniaEsperada);


        assertEquals(usuario.getContrasenia(), contraseniaEsperada);
    }

    @Test()
    public void unUsuarioCambiaSuContrasenia() {
        Usuario usuario = new Usuario("carlitos", "ASDhdursososososksms");

        String contraseniaEsperada = "f7gvfSDhdursososososksms";
        usuario.cambiarContrasenia(contraseniaEsperada);


        assertEquals(usuario.getContrasenia(), contraseniaEsperada);
    }
}
