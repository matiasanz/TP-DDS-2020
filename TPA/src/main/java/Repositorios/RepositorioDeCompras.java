package Repositorios;

import Compra.*;
import Direccion.*;
import MedioDePago.PagoEnEfectivo;
import Moneda.CodigoMoneda;
import Presupuesto.Presupuesto;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;
import Usuario.Usuario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDeCompras {

    public List<Compra> compras;
    
    //Atributos mock
    private final Item item50 = new Item("Item 1", 1, BigDecimal.valueOf(50.0));
    private final Item item40Con50 = new Item("Item 1", 1, BigDecimal.valueOf(40.5));
    private final Item item9Con50 = new Item("Item 1", 1, BigDecimal.valueOf(9.5));
    private RepositorioDeMonedas repositorioDeMonedas;    
    private final Direccion direccion = new Direccion(new RepositorioDeLocacionesMeli(), "Cervantes", 607, 5, "1407", Pais.AR);
    private final Proveedor proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccion);
    private final Presupuesto presupuesto = new Presupuesto(null, proveedor);

    //Constructor -- repo de monedas se tiene que ir
    public RepositorioDeCompras(RepositorioDeMonedas repositorioDeMonedas, List<Compra> compras) {
        this.repositorioDeMonedas = repositorioDeMonedas;
        this.compras = compras;
    }
    
    public List<Compra> getCompras() {
    	return compras;
    }

    public List<Compra> getComprasPendientesDeAprobacion() {
        return this.getComprasConPresupuestoElegido()
                .stream()
                .filter(compra -> compra.pendienteDeAprobacion())
                .collect(Collectors.toList());
    }
    
    public void agregarCompra(Compra compra) {
    	compras.add(compra);
    }
    
    public RepositorioDeCompras repositorioDelMes(LocalDate fecha){
    	return new RepositorioDeCompras(repositorioDeMonedas, comprasDelMes(fecha));
    }
    
    public List<Compra> comprasDelMes(LocalDate fechaInicio) {
        return compras
                .stream()
                .filter(compra -> compra.compraDelMes(fechaInicio))
                .collect(Collectors.toList());
    }
    
    public List<Compra> comprasSinEtiquetar(){
    	return compras.stream().filter(compra -> !compra.etiquetada()).collect(Collectors.toList());
    }

    public List<Compra> comprasConEtiqueta(String etiqueta){
  		 return compras.stream().filter(c -> c.contieneEtiqueta(etiqueta)).collect(Collectors.toList());
    }
    
	public List<String> getEtiquetas(){
		return compras.stream().map(c -> c.getEtiquetas()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
	}
	
// HARDCODEOS *********************************************************
    
    public Compra getCompraMarzo2018Amoblamiento() {

        Compra compraMarzo2018Amoblamiento = new Compra(repositorioDeMonedas, null, proveedor, LocalDate.of(2018, 3, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        compraMarzo2018Amoblamiento.agregarItem(item50);
        compraMarzo2018Amoblamiento.agregarItem(item9Con50);
        compraMarzo2018Amoblamiento.agregarEtiqueta("Amoblamiento");
        compraMarzo2018Amoblamiento.agregarPresupuesto(presupuesto);
        compraMarzo2018Amoblamiento.setPresupuestoElegido(presupuesto);

        return compraMarzo2018Amoblamiento;
    }

    public Compra getCompraFebrero2017SinEtiqueta() {

        Compra compraFebrero2017SinEtiqueta = new Compra(repositorioDeMonedas, null, proveedor, LocalDate.of(2017, 2, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        compraFebrero2017SinEtiqueta.agregarItem(item50);
        compraFebrero2017SinEtiqueta.agregarPresupuesto(presupuesto);
        compraFebrero2017SinEtiqueta.setPresupuestoElegido(presupuesto);

        return compraFebrero2017SinEtiqueta;
    }

    public Compra getCompraJulio2020JuanPerez() {

        Compra compraJulio2020JuanPerez = new Compra(repositorioDeMonedas, null, proveedor, LocalDate.of(2020, 7, 16), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        compraJulio2020JuanPerez.agregarItem(item40Con50);
        compraJulio2020JuanPerez.agregarItem(item9Con50);
        compraJulio2020JuanPerez.agregarItem(item9Con50);
        compraJulio2020JuanPerez.agregarItem(item9Con50);
        compraJulio2020JuanPerez.agregarEtiqueta("Juan Perez");
        compraJulio2020JuanPerez.agregarPresupuesto(presupuesto);
        compraJulio2020JuanPerez.setPresupuestoElegido(presupuesto);

        return compraJulio2020JuanPerez;
    }

    public Compra getCompra1Julio2020Amoblamiento() {

        Compra compra1Julio2020Amoblamiento = new Compra(repositorioDeMonedas, null, proveedor, LocalDate.of(2020, 7, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        compra1Julio2020Amoblamiento.agregarItem(item40Con50);
        compra1Julio2020Amoblamiento.agregarEtiqueta("Amoblamiento");
        compra1Julio2020Amoblamiento.agregarPresupuesto(presupuesto);
        compra1Julio2020Amoblamiento.setPresupuestoElegido(presupuesto);

        return compra1Julio2020Amoblamiento;
    }

    public Compra getCompra19Julio2020Amoblamiento() {

        Compra compra19Julio2020Amoblamiento = new Compra(repositorioDeMonedas, null, proveedor, LocalDate.of(2020, 7, 19), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        compra19Julio2020Amoblamiento.agregarItem(item9Con50);
        compra19Julio2020Amoblamiento.agregarEtiqueta("Amoblamiento");
        compra19Julio2020Amoblamiento.agregarPresupuesto(presupuesto);
        compra19Julio2020Amoblamiento.setPresupuestoElegido(presupuesto);

        return compra19Julio2020Amoblamiento;
    }

    public Compra getCompra12Julio2020SinEtiqueta() {

        Compra compra12Julio2020SinEtiqueta = new Compra(repositorioDeMonedas, null, proveedor, LocalDate.of(2020, 7, 12), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        compra12Julio2020SinEtiqueta.agregarItem(item9Con50);
        compra12Julio2020SinEtiqueta.agregarPresupuesto(presupuesto);
        compra12Julio2020SinEtiqueta.setPresupuestoElegido(presupuesto);

        return compra12Julio2020SinEtiqueta;
    }
       
    public List<Compra> getComprasConPresupuestoElegido() {

        compras.add(this.getCompraMarzo2018Amoblamiento());
        compras.add(this.getCompraFebrero2017SinEtiqueta());
        compras.add(this.getCompraJulio2020JuanPerez());
        compras.add(this.getCompra1Julio2020Amoblamiento());
        compras.add(this.getCompra19Julio2020Amoblamiento());
        compras.add(this.getCompra12Julio2020SinEtiqueta());

        return compras;
    }
}
