package TareasProgramadas.ValidacionEgresosAutomatica;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Organizacion.Organizacion;
import Organizacion.OrganizacionMock;

public class ValidacionDeEgresos implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
    	System.out.println("\n***************************** VALIDADOR DE COMPRAS DIARIO *****************************\n");
    	
    	//Obtengo una organización de prueba
    	Organizacion unaOrganizacion = OrganizacionMock.getInstance();    	
    	unaOrganizacion.validarCompras();
    	
        System.out.println("\n***************************** VALIDADOR DE COMPRAS DIARIO *****************************\n");
    }    
}
