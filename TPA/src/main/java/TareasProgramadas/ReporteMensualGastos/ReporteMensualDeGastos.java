package TareasProgramadas.ReporteMensualGastos;

import Organizacion.OrganizacionMock;
import Repositorios.RepoEntidadesDB;
import Repositorios.RepositorioDeComprasMemoria;
import Repositorios.RepositorioDeEntidades;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Compra.Compra;
import Entidad.Entidad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityTransaction;

public class ReporteMensualDeGastos implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps, Job {

	public static RepositorioDeEntidades repoEntidades = RepositorioDeEntidades.instancia;
		
    public void execute(JobExecutionContext context) {

//    	agregarDatosADB(); //
    	LocalDate fechaActual = LocalDate.now(); //LocalDate.of(2020, 07, 31);

        System.out.println("****** INICIO EJECUCION REPORTE MENSUAL DE GASTOS ******");

        withTransaction(()->{
        	
        	for(Entidad unaEntidad: repoEntidades.getAll()){
        		System.out.println(">> " + unaEntidad.getNombreFicticio());
        		Map<String, Double> resultadoReporte = generarReporteDeGastos(unaEntidad, fechaActual);
        		
        		if(resultadoReporte.isEmpty()) 
        			System.out.println("No se reportaron gastos");
        		else
        			imprimirResultadoReporte(resultadoReporte);
        	}
        
        });
        
        System.out.println("********* FIN EJECUCION REPORTE MENSUAL DE GASTOS ******\n\n");
    }
    
    private void agregarDatosADB(){
        withTransaction(()->{
        	List<Entidad> entidadesMock = OrganizacionMock.getInstance().getRepoEntidades().getAll();
        	entidadesMock.forEach(e->repoEntidades.agregar(e));        	
        });
    }
    
    public static Map<String, Double> generarReporteDeGastos(Entidad unaEntidad, LocalDate fechaInicio) {

    	RepositorioDeComprasMemoria comprasDelMes = unaEntidad.getComprasDelMes(fechaInicio);
    	List<String> etiquetasDelMes = comprasDelMes.getEtiquetas();
    	Map<String, Double> gastosRealizados = new HashMap<>();
    
        for(String etiqueta : etiquetasDelMes){
        	Double valor = calcularValorCompras(comprasDelMes.comprasConEtiqueta(etiqueta));        	
            gastosRealizados.put(etiqueta, valor);
        }
         
        List<Compra> sinEtiquetar = comprasDelMes.comprasSinEtiquetar();
        
        if(!sinEtiquetar.isEmpty()){        	
        	gastosRealizados.put("Sin Etiquetar", calcularValorCompras(sinEtiquetar));
        }
        
        return gastosRealizados;
    }

    private void imprimirResultadoReporte(Map<String, Double> resultadoReporte){
    	for (Map.Entry<String, Double> gasto : resultadoReporte.entrySet()) {
    		imprimirGasto(gasto.getKey(), gasto.getValue());
    	}
    }
    
    private static double calcularValorCompras(List<Compra> unasCompras){
    	return unasCompras.stream().map(c -> c.getValorTotal())
    			.reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
    }
    
    private void imprimirGasto(String nombreEtiqueta, Double totalCompras) {
        System.out.println("Etiqueta = " + nombreEtiqueta + ", Total Compra = " + totalCompras);
    }
}
