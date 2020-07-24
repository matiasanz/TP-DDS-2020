package BandejaDeMensajes;

import java.io.BufferedReader;
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
	
	public void ejecutar(){
        autenticarUsuario();
		testearConUsuarioPredeterminado();
        validarComprasPendientes();
	}
	
	private void autenticarUsuario(){
		System.out.println("Ingrese usuario");
		String usuarioIngresado = this.leerConsola();		
		System.out.println("Ingrese contraseña");
        String passwordIngresada = this.leerConsola();
        
        try{
        	usuario = organizacion.getUsuarioEspecifico(usuarioIngresado, passwordIngresada);
        }
        
        catch(RuntimeException unaExcepcion) {
        	System.out.println("ERROR: El usuario y/o la contraseña son incorrectos. Por favor vuelva a intentarlo. \n\n");
        	this.autenticarUsuario();
        }
	}
	
	
	private String leerConsola(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String lectura;
        
        try{
        	lectura = reader.readLine();
        } catch(Exception e){
        	throw new RuntimeException(e.getCause());
        }
        
		return lectura;  
	}
	
	private void testearConUsuarioPredeterminado() {
		organizacion.getCompras().forEach(compra -> compra.agregarUsuarioValidador(usuario));
	}
	
	private void validarComprasPendientes() {
		List<Compra> comprasPendientesDeValidacion = organizacion.comprasQuePuedeValidar(usuario);

		if(comprasPendientesDeValidacion.isEmpty()){
			System.out.println("\n***** NO SE HAN ENCONTRADO COMPRAS POR VALIDAR\n");			
			return;
		}
		
		System.out.println("\n***** SE HAN ENCONTRADO COMPRAS POR VALIDAR\n");
           
        for(Compra compraSinValidar : comprasPendientesDeValidacion){
        	compraSinValidar.validar();
            System.out.println("Se ha validado una compra y se ha obtenido el siguiente estado: " + compraSinValidar.getIndicadorDeAprobacion());
        }
	}
}
