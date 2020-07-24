import BandejaDeMensajes.BandejaDeMensajes;
import Compra.Compra;
import Compra.Estado;
import Organizacion.Organizacion;
import Organizacion.OrganizacionMock;
import Fabrica.Fabrica;

public class MainClass {

    public static void main(String[] args){
<<<<<<< HEAD
    	System.out.println("\n***************************** Inicio de sesion *****************************\n");
=======
    	System.out.println("\n***************************** INICIO VALIDADOR DE COMPRAS *****************************\n");
>>>>>>> 5db7a77184325ca9536986299cab92542fdd69d9
    	
    	//Obtengo una organización de prueba
    	Organizacion unaOrganizacion = OrganizacionMock.getInstance();
    	Compra compraPorValidar = Fabrica.compraEnEstado(Estado.PENDIENTEDEAPROBACION);
    	unaOrganizacion.getEntidades().forEach(entidad->entidad.agregarCompra(compraPorValidar));
    	
    	// Genero la aplicación
    	BandejaDeMensajes bandejaDeMensajes = new BandejaDeMensajes(unaOrganizacion);
        bandejaDeMensajes.ejecutar();
    	
<<<<<<< HEAD
        System.out.println("\n***************************** Fin de sesion *****************************\n");
=======
        System.out.println("\n***************************** FIN VALIDADOR DE COMPRAS *****************************\n");
>>>>>>> 5db7a77184325ca9536986299cab92542fdd69d9
    }

}