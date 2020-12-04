package Usuario;
import org.junit.Test;

import Exceptions.ContraseniaEntreLasDiezMilException;
import Exceptions.ContraseniaNoCumpleLongitudMinimaException;
import Exceptions.ContraseniaTieneCaracteresRepetidosException;
import Exceptions.ContraseniaTieneNombreDeUsuarioIncluidoException;

import static org.junit.Assert.assertEquals;
public class TestUsuario {

	private ValidadorUsuario validador = new ValidadorUsuario();
	 
	
    @Test(expected = ContraseniaEntreLasDiezMilException.class)
    public void contraseniaEntreLas10000peores() {
    	validador.validarContraseniaEntreLasDiezMilMasConocidas("qwerty");
    }

    @Test(expected = ContraseniaNoCumpleLongitudMinimaException.class)
    public void contraseniaNoCumpleConLongitudMinima() {
    	validador.validarContraseniaConLongitudMinima("pepe");
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidosException.class)
    public void validarCaracteresRepetidosConTresCaracteres() {
    	validador.validarCaracteresRepetidos("pepeholappp");
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidosException.class)
    public void validarCaracteresRepetidosConCuatroCaracteres() {
    	validador.validarCaracteresRepetidos("pepeholappppp");
    }

    @Test(expected = ContraseniaTieneCaracteresRepetidosException.class)
    public void validarCaracteresRepetidosConDosCaracteres() {     
    	validador.validarCaracteresRepetidos("pepeholapp");
    }
    
    @Test(expected = ContraseniaTieneNombreDeUsuarioIncluidoException.class)
    public void validarCaracteresQueIncluyenElNombreDeUsuario() {
    	validador.validarUsuarioNoContenidoEnContrasenia("carlitos", "elmascrackesCarlitos");
    }

    @Test()
    public void usuarioConContraseniaValidaSeCreaCorrectamente() {
        String contraseniaEsperada = "ASDhdursososososksms";
        Usuario usuario = new Usuario("carlitos", contraseniaEsperada, null);
        validador.validarContrasenia(usuario.getContrasenia(),usuario.getUsername());

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
