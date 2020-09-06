package TareasProgramadas.ReporteMensualGastos;

import Entidad.Entidad;
import Repositorios.RepositorioDeEntidades;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import java.time.LocalDate;
import java.util.Map;

public class ReporteMensualDeGastos implements Job {

    public void execute(JobExecutionContext context) {

        Entidad entidad = new RepositorioDeEntidades(new RepositorioDeMonedasMeli()).getEntidadConCompras();
        LocalDate fechaActual = LocalDate.of(2020, 07, 31);
        Map<String, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaActual);

        System.out.println("INICIO EJECUCION REPORTE MENSUAL DE GASTOS");
        for (Map.Entry<String, Double> item : resultadoReporte.entrySet()) {
            imprimirItem(item.getKey(), item.getValue());
        }
    }

    private void imprimirItem(String nombreEtiqueta, Double totalCompras) {

        System.out.println("Etiqueta = " + nombreEtiqueta + ", Total Compra = " + totalCompras);
    }
}
