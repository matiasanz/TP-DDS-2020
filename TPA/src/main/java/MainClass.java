import BandejaDeMensajes.BandejaDeMensajes;
import Compra.Compra;
import Compra.Estado;
import Organizacion.Organizacion;

import Fabrica.Fabrica;

public class MainClass {

    public static void main(String[] args){
    	System.out.println("\n***************************** INICIO VALIDADOR DE COMPRAS *****************************\n");
    	
    	//Obtengo una organización de prueba
    	Organizacion unaOrganizacion = Fabrica.organizacionStub();
    	Compra compraPorValidar = Fabrica.compraEnEstado(Estado.PENDIENTEDEAPROBACION);
    	unaOrganizacion.getEntidades().forEach(entidad->entidad.agregarCompra(compraPorValidar));
    	
    	// Genero la aplicación
    	BandejaDeMensajes bandejaDeMensajes = new BandejaDeMensajes(unaOrganizacion);
        bandejaDeMensajes.ejecutar();
    	
        System.out.println("\n***************************** FIN VALIDADOR DE COMPRAS *****************************\n");
    }

}