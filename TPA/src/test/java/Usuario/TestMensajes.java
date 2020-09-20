package Usuario;
import org.junit.Test;

import Compra.Compra;
import Factory.ComprasFactory;
import Factory.UsuariosFactory;

import static org.junit.Assert.*;

import org.junit.After;

public class TestMensajes {
	
	Usuario usuario1 = UsuariosFactory.usuarioStub();
	
	@After
	public void end(){
		usuario1.vaciarBandeja();
	}
	
	@Test()
	public void unUsuarioSeCreaSinNotificaciones(){
		assert(usuario1.getMensajes().isEmpty());
	}
	
	@Test()
    public void unUsuarioRecibeYEliminaMensajes(){
    	String notificacion = "Alerta alerta, estas por romper la dieta";
    	
    	usuario1.notificarEvento(notificacion);
    	assertMensajeUnico(usuario1,notificacion);
    	
    	usuario1.vaciarBandeja();
    	assertTrue(usuario1.getMensajes().isEmpty());
    }
	
    @Test
    public void unaCompraSeValidaYNotificaATodosSusUsuarios(){
    	Usuario usuario1 = UsuariosFactory.usuarioStub();
    	Usuario usuario2 = UsuariosFactory.usuarioStub();    	
    	Compra compra = ComprasFactory.compraParaUsuario(usuario1);
    	compra.agregarUsuarioValidador(usuario2);
    	
    	compra.validar();
    	
    	assertEquals(1, usuario2.getMensajes().size());    	
    	
    	String motivo = "No se ha seleccionado ningun presupuesto";
    	assertMensajeUnico(usuario1, "-----------<Una Compra ha sido rechazada>----------\n"
        		+ "[Motivo: "+ motivo  + "]");
    }
    
    @Test
    public void usuarioRecibeMasDeUnaNotificacion(){
    	ComprasFactory.compraParaUsuario(usuario1).validar();
    	ComprasFactory.compraParaUsuario(usuario1).validar();
    	
    	assertEquals(2, usuario1.getMensajes().size());
    }
    
    
    //************ Auxiliares **************
    
    public void assertMensajeUnico(Usuario usuario, String contenido){
    	assertEquals(1,usuario.getMensajes().size());
    	assertEquals(contenido,usuario.getMensajes().get(0));
    }
}
