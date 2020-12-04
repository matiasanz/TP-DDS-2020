package Repositorios.RepositorioDeCompras;

import Compra.Compra;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CompraHelper {
    public static List<Compra> comprasSinEtiquetar(List<Compra> compras) {
        return compras.stream().filter(compra -> !compra.etiquetada()).collect(Collectors.toList());
    }

    public static List<Compra> comprasConEtiqueta(List<Compra> compras, String etiqueta) {
        return compras.stream().filter(c -> c.contieneEtiqueta(etiqueta)).collect(Collectors.toList());
    }

    public static List<String> getEtiquetas(List<Compra> compras) {
        return compras.stream().map(c -> c.getEtiquetas()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
    }

    public static double calcularValorCompras(List<Compra> unasCompras) {
        return unasCompras.stream().map(c -> c.getValorTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
    }

    public static List<Compra> comprasDelMes(List<Compra> compras, LocalDate fechaInicio) {
        return compras
                .stream()
                .filter(compra -> compra.compraDelMes(fechaInicio))
                .collect(Collectors.toList());
    }
}
