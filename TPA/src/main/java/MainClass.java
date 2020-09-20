import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import Organizacion.IngresoFallidoException;
import Organizacion.Organizacion;
import Organizacion.OrganizacionMock;
import Usuario.Usuario;

public class MainClass {
	private static Organizacion organizacion = OrganizacionMock.getInstance();
	private static Usuario usuario;
	
    public static void main(String[] args){
    	imprimirPorPantalla("\n***************************** Inicio de sesion *****************************\n");
    	    	
    	autenticarUsuario();
    	
        leerBandejaDeMensajes();
        
        vaciarBandejaOpcional();
        
        imprimirPorPantalla("\n***************************** Fin de sesion *****************************\n");
    }

	private static void autenticarUsuario(){
		System.out.println("Ingrese usuario");
		String usuarioIngresado = leerConsola();		
		System.out.println("Ingrese contraseña");
        String passwordIngresada = leerConsola();
        
        try{
        	usuario = organizacion.getUsuarioEspecifico(usuarioIngresado, passwordIngresada);
        }
        
        catch(IngresoFallidoException unaExcepcion) {
        	System.out.println("ERROR: El usuario y/o la contraseña son incorrectos. Por favor vuelva a intentarlo. \n\n");
        	autenticarUsuario();
        }
	}
	
	private static void leerBandejaDeMensajes() {
		List<String>mensajes = usuario.getMensajes();
		
		if(mensajes.isEmpty()){
			System.out.println("\n***** LA BANDEJA DE MENSAJES SE ENCUENTRA VACIA\n");			
			return;
		}
		
		System.out.println("\n***** SE HAN ENCONTRADO COMPRAS POR VALIDAR\n");
		mensajes.stream().forEach(mensaje->imprimirPorPantalla(mensaje));
	}
	
	private static void vaciarBandejaOpcional()
	{
		imprimirPorPantalla("Desea vaciar la bandeja? (y=si/n=no)");
		String respuesta = leerConsola();
		if(respuesta.equalsIgnoreCase("y")){
			usuario.vaciarBandeja();
			imprimirPorPantalla("Los mensajes han sido eliminados");
		}
	}
	
//	********************* Auxiliares ***********************
	
	private static String leerConsola(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String lectura;
        
        try{
        	lectura = reader.readLine();
        } catch(Exception e){
        	throw new RuntimeException(e.getCause());
        }
        
		return lectura;  
	}
	
	private static void imprimirPorPantalla(String cadena){
		System.out.println(cadena);
	}
}