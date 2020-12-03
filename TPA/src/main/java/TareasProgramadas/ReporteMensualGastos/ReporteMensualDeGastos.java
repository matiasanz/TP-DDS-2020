package TareasProgramadas.ReporteMensualGastos;

import Repositorios.RepositorioDeCompras.CompraHelper;
import Repositorios.RepositorioDeCompras.RepoComprasDB;
import Compra.Compra;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteMensualDeGastos {

    static RepoComprasDB repoDeCompras = new RepoComprasDB();

    public void execute() {
        System.out.println("****** INICIO EJECUCION REPORTE MENSUAL DE GASTOS ******");

        Map<String, Double> resultadoReporte = generarReporteDeGastos(repoDeCompras.getAll(), LocalDate.now());

        if (resultadoReporte.isEmpty())
            System.out.println("No se reportaron gastos");
        else
            imprimirResultadoReporte(resultadoReporte);


        System.out.println("********* FIN EJECUCION REPORTE MENSUAL DE GASTOS ******\n\n");
    }

    public static Map<String, Double> generarReporteDeGastos(List<Compra> compras, LocalDate fechaReporte) {

        List<Compra> comprasDelMes = CompraHelper.comprasDelMes(compras, fechaReporte);

        Map<String, Double> gastosRealizados = new HashMap<>();

        for (String etiqueta : CompraHelper.getEtiquetas(comprasDelMes)) {
            Double valor = CompraHelper.calcularValorCompras(CompraHelper.comprasConEtiqueta(comprasDelMes, etiqueta));
            gastosRealizados.put(etiqueta, valor);
        }

        List<Compra> sinEtiquetar = CompraHelper.comprasSinEtiquetar(comprasDelMes);

        if (!sinEtiquetar.isEmpty()) {
            gastosRealizados.put("Sin Etiquetar", CompraHelper.calcularValorCompras(sinEtiquetar));
        }

        return gastosRealizados;
    }

    private void imprimirResultadoReporte(Map<String, Double> resultadoReporte) {
        for (Map.Entry<String, Double> gasto : resultadoReporte.entrySet()) {
            imprimirGasto(gasto.getKey(), gasto.getValue());
        }
    }

    private void imprimirGasto(String nombreEtiqueta, Double totalCompras) {
        System.out.println("Etiqueta = " + nombreEtiqueta + ", Total Compra = " + totalCompras);
    }
}
