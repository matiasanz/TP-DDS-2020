package TareasProgramadas.ValidacionEgresosAutomatica;

import BandejaDeMensajes.BandejaDeMensajes;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Fabrica.Fabrica;
import Organizacion.Organizacion;

import java.io.IOException;

public class ValidacionDeEgresos implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
    	System.out.println("\n***************************** VALIDADOR DE COMPRAS DIARIO *****************************\n");
    	
    	//Obtengo una organización de prueba
    	Organizacion unaOrganizacion = Fabrica.organizacionStub();
    	
    	// Genero la aplicación
    	BandejaDeMensajes bandejaDeMensajes = new BandejaDeMensajes(unaOrganizacion);
        try {
			bandejaDeMensajes.ejecutar();
		} catch (IOException e) {
			System.out.println("\nHa ocurrido un error con el usuario insertado\n");
		}
    	
        System.out.println("\n***************************** VALIDADOR DE COMPRAS DIARIO *****************************\n");

        
    }
}
