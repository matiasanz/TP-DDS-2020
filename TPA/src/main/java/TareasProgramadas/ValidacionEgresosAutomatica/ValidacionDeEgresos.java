package TareasProgramadas.ValidacionEgresosAutomatica;

import Repositorios.RepositorioDeCompras.RepositorioDeCompras;

import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Compra.Compra;
import Compra.Estado;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidacionDeEgresos implements Job {

    private RepositorioDeCompras repositorioCompras = new RepositorioDeCompras(new RepositorioDeMonedasMeli());

    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<Compra> comprasPendientesDeValidacion = repositorioCompras.getComprasPendientesDeAprobacion();
        LocalDate fechaActual = LocalDate.now();
        //Revisar esta línea
        List<Estado> resultadoReporte = comprasPendientesDeValidacion.stream().map(unaCompra -> unaCompra.getIndicadorDeAprobacion()).collect(Collectors.toList());

        System.out.println("INICIO EJECUCION VALIDADOR MENSUAL DE LOS ESTADOS DE COMPRAS");
        /*
        for (List<Estado> item : resultadoReporte.entrySet()) {
            //Seteo el estado de las compras
        }
        */
    }
}
