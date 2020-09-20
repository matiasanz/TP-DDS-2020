package Usuario;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class TestUsuario {


    @Test(expected = ContraseniaEntreLasDiezMilException.class)
    public void contraseniaEntreLas10000peores() {
        new Usuario("guido", "qwerty");
    }

    @Test(expected = ContraseniaNoCumpleLongitudMinimaException.class)
    public void contraseniaNoCumpleConLongitudMinima() {
        new Usuario("carlitos", "pepe");
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidosException.class)
    public void validarCaracteresRepetidosConTresCaracteres() {
        new Usuario("carlitos", "pepeholappp");
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidosException.class)
    public void validarCaracteresRepetidosConCuatroCaracteres() {
        new Usuario("carlitos", "pepeholappppp");
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidosException.class)
    public void validarCaracteresRepetidosConDosCaracteres() {
        String contraseniaEsperada = "pepeholapp";
        new Usuario("carlitos", contraseniaEsperada);
    }
    
    @Test(expected = ContraseniaTieneNombreDeUsuarioIncluidoException.class)
    public void validarCaracteresQueIncluyenElNombreDeUsuario() {
        String contraseniaEsperada = "elmascrackesCarlitos";
        new Usuario("carlitos", contraseniaEsperada);
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
