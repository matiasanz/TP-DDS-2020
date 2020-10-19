package TareasProgramadas.ValidacionEgresosAutomatica;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Compra.Compra;
import Factory.ComprasFactory;
import Repositorios.RepoComprasDB;
import Repositorios.RepositorioDeComprasMemoria;

public class ValidacionDeEgresos implements Job {

	public static RepositorioDeComprasMemoria repoCompras = new RepositorioDeComprasMemoria();
//	public static RepoComprasDB repoCompras = new RepoComprasDB(); 
	
	private static EntityTransaction transaccion = PerThreadEntityManagers.getEntityManager().getTransaction();
	
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	agregarDatosADB(); //

    	System.out.println("\n***************************** INICIO VALIDADOR DE COMPRAS DIARIO *****************************\n");
    
    	transaccion.begin();
    	
    	List<Compra> comprasAValidar = repoCompras.getComprasPendientesDeAprobacion();    	
    	comprasAValidar.forEach(c->validarCompra(c));
    	
    	transaccion.commit();

    	System.out.println("\n***************************** " + comprasAValidar.size() + " COMPRAS FUERON VALIDADAS *****************************\n");
    }
    
    private static void agregarDatosADB(){
    	transaccion.begin();
    	repoCompras.agregar(ComprasFactory.getCompra19Julio2020Amoblamiento());
        repoCompras.agregar(ComprasFactory.getCompraFebrero2017SinEtiqueta());
        transaccion.commit();
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
