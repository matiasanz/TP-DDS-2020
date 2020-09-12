import BandejaDeMensajes.BandejaDeMensajes;
import Compra.Compra;
import Compra.Estado;
import Factory.ComprasFactory;
import Organizacion.Organizacion;
import Organizacion.OrganizacionMock;

public class MainClass {

    public static void main(String[] args){
    	System.out.println("\n***************************** Inicio de sesion *****************************\n");
    	
    	//Obtengo una organización de prueba
    	Organizacion unaOrganizacion = OrganizacionMock.getInstance();
    	Compra compraPorValidar = ComprasFactory.compraEnEstado(Estado.PENDIENTEDEAPROBACION);
    	unaOrganizacion.getEntidades().forEach(entidad->entidad.agregarCompra(compraPorValidar));
    	
    	// Genero la aplicación
    	BandejaDeMensajes bandejaDeMensajes = new BandejaDeMensajes(unaOrganizacion);
        bandejaDeMensajes.ejecutar();
    	
        System.out.println("\n***************************** Fin de sesion *****************************\n");
    }

}