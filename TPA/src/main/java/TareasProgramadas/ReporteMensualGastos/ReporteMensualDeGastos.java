package TareasProgramadas.ReporteMensualGastos;

import Entidad.Entidad;
import Etiqueta.Etiqueta;
import Repositorios.RepositorioDeEntidades.RepositorioDeEntidades;
import Repositorios.RepositorioDeEtiquetas.RepositorioDeEtiquetas;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import java.time.LocalDate;
import java.util.Map;

public class ReporteMensualDeGastos implements Job {

    private final RepositorioDeEtiquetas repoEtiquetas = new RepositorioDeEtiquetas();

    public void execute(JobExecutionContext context) {

        Entidad entidad = new RepositorioDeEntidades().getEntidadConCompras();
        LocalDate fechaActual = LocalDate.now();
        Map<Integer, Double> resultadoReporte = entidad.obtenerGastosRealizados(fechaActual);

        System.out.println("INICIO EJECUCION REPORTE MENSUAL DE GASTOS");
        for (Map.Entry<Integer, Double> item : resultadoReporte.entrySet()) {
            imprimirItem(item.getKey(), item.getValue());
        }
    }

    private void imprimirItem(Integer identificador, Double totalCompras) {

        Etiqueta etiqueta = repoEtiquetas.getEtiquetaDadoIdentificador(identificador);
        System.out.println("Etiqueta = " + etiqueta.getNombre() + ", Total Compra = " + totalCompras);
    }
}
