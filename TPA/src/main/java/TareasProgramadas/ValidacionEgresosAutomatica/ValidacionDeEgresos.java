package TareasProgramadas.ValidacionEgresosAutomatica;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Compra.Compra;
import Factory.ComprasFactory;
import Repositorios.RepoComprasDB;
import Repositorios.RepositorioDeComprasMemoria;

public class ValidacionDeEgresos implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps, Job {

	public static RepoComprasDB repoCompras = new RepoComprasDB(); 
		
    public void execute(JobExecutionContext context) throws JobExecutionException {
//    	agregarDatosADB(); //

    	withTransaction(()->{    		

    		System.out.println("\n***************************** INICIO VALIDADOR DE COMPRAS DIARIO *****************************\n");
    		List<Compra> comprasAValidar = repoCompras.getComprasPendientesDeAprobacion();    	
    		comprasAValidar.forEach(c->validarCompra(c));
    		System.out.println("\n***************************** " + comprasAValidar.size() + " COMPRAS FUERON VALIDADAS *****************************\n");

    	});

    }
    
    private void agregarDatosADB(){
    	withTransaction(()->{
    		repoCompras.agregar(ComprasFactory.getCompra19Julio2020Amoblamiento());
    		repoCompras.agregar(ComprasFactory.getCompraFebrero2017SinEtiqueta());
    	});
    }
    
    public static void validarCompra(Compra unaCompra){

    	String mensaje;
        try{
            unaCompra.validar();
            unaCompra.aprobar();
            mensaje = "-----------<Una Compra ha sido aprobada>----------\n";
        } catch(RuntimeException unaExcepcion){
            unaCompra.rechazar();
            mensaje = "-----------<Una Compra ha sido rechazada>----------\n"
            		+ "[Motivo: "+unaExcepcion.getMessage() + "]";
        }
        
        unaCompra.notificarUsuarios(mensaje);
    }
}
