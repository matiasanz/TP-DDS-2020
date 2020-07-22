package Repositorios.RepositorioDeCompras;
import Compra.*;
import Direccion.*;
import Etiqueta.*;
import MedioDePago.PagoEnEfectivo;
import Moneda.CodigoMoneda;
import Presupuesto.Presupuesto;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeEtiquetas.RepositorioDeEtiquetas;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDeCompras {

    private final Item item50 = new Item("Item 1", 1, BigDecimal.valueOf(50.0));
    private final Item item40Con50 = new Item("Item 1", 1, BigDecimal.valueOf(40.5));
    private final Item item9Con50 = new Item("Item 1", 1, BigDecimal.valueOf(9.5));
    private final Direccion direccion = new Direccion(new RepositorioDeLocacionesMeli(), "Cervantes", 607, 5, "1407", Pais.AR);
    private final Proveedor proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccion);
    private final Presupuesto presupuesto = new Presupuesto(null, proveedor);
    private final Etiqueta etiquetaAmoblamiento  = new EtiquetaPersonalizable( 1,"amoblamiento");
    private final Etiqueta etiquetaProveedorJuanPerez = new EtiquetaProveedor(2, proveedor);

    public List<Compra> getCompras() {
        List<Compra> compras = new ArrayList<Compra>();

        Compra compraMarzo2018Amoblamiento = new Compra(new RepositorioDeMonedasMeli(), new RepositorioDeEtiquetas(), null, proveedor, LocalDate.of(2018, 3, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, null);
        compraMarzo2018Amoblamiento.agregarItem(item50);
        compraMarzo2018Amoblamiento.agregarItem(item9Con50);
        compraMarzo2018Amoblamiento.setEtiqueta(getEtiquetaAmoblamiento());
        compraMarzo2018Amoblamiento.generarPresupuesto(presupuesto);
        compraMarzo2018Amoblamiento.setPresupuestoElegido(presupuesto);
        compras.add(compraMarzo2018Amoblamiento);

        Compra compraFebrero2017SinEtiqueta = new Compra(new RepositorioDeMonedasMeli(), new RepositorioDeEtiquetas(), null, proveedor, LocalDate.of(2017, 2, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, null);
        compraFebrero2017SinEtiqueta.agregarItem(item50);
        compraFebrero2017SinEtiqueta.generarPresupuesto(presupuesto);
        compraFebrero2017SinEtiqueta.setPresupuestoElegido(presupuesto);
        compras.add(compraFebrero2017SinEtiqueta);

        Compra compraJulio2020JuanPerez = new Compra(new RepositorioDeMonedasMeli(), new RepositorioDeEtiquetas(), null, proveedor, LocalDate.of(2020, 7, 16), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, null);
        compraJulio2020JuanPerez.agregarItem(item40Con50);
        compraJulio2020JuanPerez.agregarItem(item9Con50);
        compraJulio2020JuanPerez.agregarItem(item9Con50);
        compraJulio2020JuanPerez.agregarItem(item9Con50);
        compraJulio2020JuanPerez.setEtiqueta(getEtiquetaProveedorJuanPerez());
        compraJulio2020JuanPerez.generarPresupuesto(presupuesto);
        compraJulio2020JuanPerez.setPresupuestoElegido(presupuesto);
        compras.add(compraJulio2020JuanPerez);

        Compra compra1Julio2020Amoblamiento = new Compra(new RepositorioDeMonedasMeli(), new RepositorioDeEtiquetas(), null, proveedor, LocalDate.of(2020, 7, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, null);
        compra1Julio2020Amoblamiento.agregarItem(item40Con50);
        compra1Julio2020Amoblamiento.setEtiqueta(getEtiquetaAmoblamiento());
        compra1Julio2020Amoblamiento.generarPresupuesto(presupuesto);
        compra1Julio2020Amoblamiento.setPresupuestoElegido(presupuesto);
        compras.add(compra1Julio2020Amoblamiento);

        Compra compra19Julio2020Amoblamiento = new Compra(new RepositorioDeMonedasMeli(), new RepositorioDeEtiquetas(), null, proveedor, LocalDate.of(2020, 7, 19), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, null);
        compra19Julio2020Amoblamiento.agregarItem(item9Con50);
        compra19Julio2020Amoblamiento.setEtiqueta(getEtiquetaAmoblamiento());
        compra19Julio2020Amoblamiento.generarPresupuesto(presupuesto);
        compra19Julio2020Amoblamiento.setPresupuestoElegido(presupuesto);
        compras.add(compra19Julio2020Amoblamiento);

        Compra compra12Julio2020SinEtiqueta = new Compra(new RepositorioDeMonedasMeli(), new RepositorioDeEtiquetas(), null, proveedor, LocalDate.of(2020, 7, 12), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, null);
        compra12Julio2020SinEtiqueta.agregarItem(item9Con50);
        compra12Julio2020SinEtiqueta.generarPresupuesto(presupuesto);
        compra12Julio2020SinEtiqueta.setPresupuestoElegido(presupuesto);
        compras.add(compra12Julio2020SinEtiqueta);

        return compras;
    }
    
    public List<Compra> getComprasPendientesDeAprobacion(){
    	return this.getCompras().stream().filter(compra -> compra.getIndicadorDeAprobacion() == Estado.PENDIENTEDEAPROBACION).collect(Collectors.toList());
    }

    public Etiqueta getEtiquetaAmoblamiento() {
        return etiquetaAmoblamiento;
    }

    public Etiqueta getEtiquetaProveedorJuanPerez() {
        return etiquetaProveedorJuanPerez;
    }
}
