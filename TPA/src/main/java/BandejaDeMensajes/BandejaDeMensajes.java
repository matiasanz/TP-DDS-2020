package BandejaDeMensajes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import Compra.Compra;
import Organizacion.Organizacion;
import Usuario.Usuario;

public class BandejaDeMensajes {
	private Organizacion organizacion;
	private Usuario usuario;
	
	public BandejaDeMensajes(Organizacion organizacion){
		this.organizacion = organizacion;
	}
	
	public void ejecutar() throws IOException {
        autenticarUsuario();
        validarComprasPendientes();
	}
	
	private void autenticarUsuario() throws IOException {
		System.out.println("Ingrese el nombre de usuario: ");
        BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in)); 
		String usuarioIngresado = userReader.readLine();
		
		System.out.println("Ingrese la contraseña: ");
		BufferedReader passReader = new BufferedReader(new InputStreamReader(System.in)); 
        String passwordIngresada = passReader.readLine();
        
        this.validarUsuario(usuarioIngresado, passwordIngresada);
	}
	
	private void validarUsuario(String nombreUsuario, String password) throws IOException {
		try {
			organizacion.autenticarUsuario(nombreUsuario, password);
			usuario = organizacion.getUsuarioEspecifico(nombreUsuario);
		} catch(RuntimeException unaExcepcion) {
			System.out.println("ERROR: El usuario especificado no pertenece a la organización establecida. Por favor vuelva a intentarlo. \n\n");
			this.autenticarUsuario();
		}
	}
	
	private void testearConUsuarioPredeterminado() {
		organizacion.getCompras().forEach(compra -> compra.agregarUsuarioValidador(usuario));
	}
	
	private void validarComprasPendientes() {
		try {
			testearConUsuarioPredeterminado();
			List<Compra> comprasPendientesDeValidacion = organizacion.comprasQuePuedeValidar(usuario);
			System.out.println("\n***** SE HAN ENCONTRADO COMPRAS POR VALIDAR\n");
	           
	        for(Compra compraSinValidar : comprasPendientesDeValidacion){
	        	compraSinValidar.validar();
	            System.out.println("Se ha validado una compra y se ha obtenido el siguiente estado: " + compraSinValidar.getIndicadorDeAprobacion());
	        }
		} catch(RuntimeException unaExcepcion) {
	    	System.out.println("\n***** NO SE HAN ENCONTRADO COMPRAS PENDIENTES DE VALIDACIÓN\n");
	    }
	}
}
