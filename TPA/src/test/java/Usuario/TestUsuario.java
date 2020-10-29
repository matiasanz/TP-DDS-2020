package Usuario;
import org.junit.Test;

import Exceptions.ContraseniaEntreLasDiezMilException;
import Exceptions.ContraseniaNoCumpleLongitudMinimaException;
import Exceptions.ContraseniaTieneCaracteresRepetidosException;
import Exceptions.ContraseniaTieneNombreDeUsuarioIncluidoException;

import static org.junit.Assert.assertEquals;
public class TestUsuario {


    @Test(expected = ContraseniaEntreLasDiezMilException.class)
    public void contraseniaEntreLas10000peores() {
        new Usuario("guido", "qwerty", null);
    }

    @Test(expected = ContraseniaNoCumpleLongitudMinimaException.class)
    public void contraseniaNoCumpleConLongitudMinima() {
        new Usuario("carlitos", "pepe", null);
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidosException.class)
    public void validarCaracteresRepetidosConTresCaracteres() {
        new Usuario("carlitos", "pepeholappp", null);
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidosException.class)
    public void validarCaracteresRepetidosConCuatroCaracteres() {
        new Usuario("carlitos", "pepeholappppp", null);
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidosException.class)
    public void validarCaracteresRepetidosConDosCaracteres() {
        String contraseniaEsperada = "pepeholapp";
        new Usuario("carlitos", contraseniaEsperada, null);
    }
    
    @Test(expected = ContraseniaTieneNombreDeUsuarioIncluidoException.class)
    public void validarCaracteresQueIncluyenElNombreDeUsuario() {
        String contraseniaEsperada = "elmascrackesCarlitos";
        new Usuario("carlitos", contraseniaEsperada, null);
    }

    @Test()
    public void validarContraseniaUsandoContraseniaValida() {
        String contraseniaEsperada = "ASDhdursososososksms";
        Usuario usuario = new Usuario("carlitos", contraseniaEsperada, null);


        assertEquals(usuario.getContrasenia(), contraseniaEsperada);
    }

    @Test()
    public void unUsuarioCambiaSuContrasenia() {
        Usuario usuario = new Usuario("carlitos", "ASDhdursososososksms", null);

        String contraseniaEsperada = "f7gvfSDhdursososososksms";
        usuario.cambiarContrasenia(contraseniaEsperada);


        assertEquals(usuario.getContrasenia(), contraseniaEsperada);
    }
}
