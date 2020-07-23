import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import Compra.Compra;
import Compra.Estado;
import Organizacion.Organizacion;
import Presupuesto.Presupuesto;
import Repositorios.RepositorioDeMensajes.RepositorioDeMensajes;
import Usuario.Usuario;
import Fabrica.Fabrica;

public class MainClass {

    public static void main(String[] args) throws IOException {
    	
    	// Genero la organizacion
        Organizacion unaOrganizacion = Fabrica.organizacionStub();
        
        // Ingreso un usuario
        BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in)); 
		String usuarioIngresado = userReader.readLine();
		
		BufferedReader passReader = new BufferedReader(new InputStreamReader(System.in)); 
        String passwordIngresada = passReader.readLine();
        
        // Valido el usuario
        unaOrganizacion.autenticarUsuario(usuarioIngresado, passwordIngresada);
        Usuario usuario = new Usuario(usuarioIngresado, passwordIngresada);
        
        // Cargo las compras que pueden ser validadas por el usuario
        List<Compra> comprasPendientesDeValidacion = unaOrganizacion.comprasQuePuedeValidar(usuario);
        
        if(comprasPendientesDeValidacion.size() > 0) {
            // Realizo la ejecución
            System.out.println("\n***************************** VALIDANDO COMPRAS PENDIENTES *****************************\n");
            
            for(Compra compraSinValidar : comprasPendientesDeValidacion){
                compraSinValidar.validar();
                System.out.println("Se ha validado una compra y se ha obtenido el siguiente estado: " + compraSinValidar.getIndicadorDeAprobacion());
            }
            
            System.out.println("\n***************************** VALIDANDO COMPRAS PENDIENTES *****************************\n");
        }
        else {
        	System.out.println("\n***************************** NO HAY COMPRAS PENDIENTES DE VALIDACIÓN POR EL MOMENTO *****************************\n");
        }
                
    }

}