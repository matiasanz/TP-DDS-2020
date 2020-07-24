import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import BandejaDeMensajes.BandejaDeMensajes;
import Compra.Compra;
import Compra.Estado;
import Organizacion.Organizacion;
import Presupuesto.Presupuesto;
import Repositorios.RepositorioDeMensajes.RepositorioDeMensajes;
import Usuario.Usuario;
import Fabrica.Fabrica;

public class MainClass {

    public static void main(String[] args) throws IOException {
    	System.out.println("\n***************************** VALIDADOR DE COMPRAS *****************************\n");
    	
    	//Obtengo una organización de prueba
    	Organizacion unaOrganizacion = Fabrica.organizacionStub();
    	
    	// Genero la aplicación
    	BandejaDeMensajes bandejaDeMensajes = new BandejaDeMensajes(unaOrganizacion);
        bandejaDeMensajes.ejecutar();
    	
        System.out.println("\n***************************** VALIDADOR DE COMPRAS *****************************\n");
    }

}