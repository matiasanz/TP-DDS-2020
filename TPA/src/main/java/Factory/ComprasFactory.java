package Factory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Compra.Compra;
import Compra.Item;
import Compra.Estado;
import Direccion.Direccion;
import Direccion.Pais;
import Entidad.Clasificacion;
import Entidad.Empresa;
import Entidad.EntidadJuridica;
import MedioDePago.MedioDePago;
import MedioDePago.PagoEnEfectivo;
import MedioDePago.DineroEnCuenta;
import Moneda.CodigoMoneda;
import Presupuesto.Presupuesto;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;
import Repositorios.RepositorioDeMonedas.*;
import Usuario.Usuario;

public class ComprasFactory {
	private static RepositorioDeMonedas repositorioDeMonedas = new RepositorioDeMonedasMock();
    private static Item item50 = ItemsFactory.itemValuadoEn(50.0);
    private static Item item40Con50 = ItemsFactory.itemValuadoEn(40.5);
    private static Item item9Con50 = ItemsFactory.itemValuadoEn(9.5);
    private static Direccion direccion = new Direccion(new RepositorioDeLocacionesMeli(), "Cervantes", 607, 5, "1407", Pais.AR);
    private static Proveedor proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccion);

	public static Compra compraSinEtiquetas(EntidadJuridica entidad, Proveedor proveedor, MedioDePago medioDePago){
        return new Compra(new RepositorioDeMonedasMock(), entidad,  LocalDate.now(), medioDePago, CodigoMoneda.ARS, 1, new LinkedList<Usuario>());
	}
	
    public static Compra getCompraMarzo2018Amoblamiento() {
        Compra compraMarzo2018Amoblamiento = new Compra(repositorioDeMonedas, null, LocalDate.of(2018, 3, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        compraMarzo2018Amoblamiento.agregarEtiqueta("Amoblamiento");
        List<Item> items = Arrays.asList(item50, item9Con50);
        Presupuesto presupuestoElegido = presupuestoConItems(items);
        
        compraMarzo2018Amoblamiento.agregarPresupuesto(presupuestoElegido);
        compraMarzo2018Amoblamiento.setPresupuestoElegido(presupuestoElegido);
        return compraMarzo2018Amoblamiento;
    }

    public static Compra getCompraFebrero2017SinEtiquetaNiPresupuestos() {
        Compra compraFebrero2017SinEtiqueta = new Compra(repositorioDeMonedas, null, LocalDate.of(2017, 2, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        List<Item> items = Arrays.asList(item50);
        return compraFebrero2017SinEtiqueta;
    }

    public static Compra getCompraFebrero2017SinEtiqueta() {
        Compra compraFebrero2017SinEtiqueta = new Compra(repositorioDeMonedas, null, LocalDate.of(2017, 2, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        List<Item> items = Arrays.asList(item50);
      
        Presupuesto presupuestoElegido = presupuestoConItems(items);
     
        
        compraFebrero2017SinEtiqueta.agregarPresupuesto(presupuestoElegido);
        compraFebrero2017SinEtiqueta.setPresupuestoElegido(presupuestoElegido);
        return compraFebrero2017SinEtiqueta;
    }
    public static Compra getCompraJulio2020JuanPerez() {
    	Compra compraJulio2020JuanPerez = new Compra(repositorioDeMonedas, null, LocalDate.of(2020, 7, 16), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());

        List<Item> items = Arrays.asList(item40Con50, item9Con50, item9Con50, item9Con50);	
       
        Presupuesto presupuestoElegido = presupuestoConItems(items);
        compraJulio2020JuanPerez.agregarEtiqueta("Juan Perez");
        compraJulio2020JuanPerez.agregarPresupuesto(presupuestoElegido);
        compraJulio2020JuanPerez.setPresupuestoElegido(presupuestoElegido);
        return compraJulio2020JuanPerez;
    }
    public static Compra getCompra1Julio2020Amoblamiento() {
        Compra compra1Julio2020Amoblamiento = new Compra(repositorioDeMonedas, null, LocalDate.of(2020, 7, 1), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        
        List<Item> items = Arrays.asList(item40Con50);
        Presupuesto presupuestoElegido = presupuestoConItems(items);
        
        compra1Julio2020Amoblamiento.agregarEtiqueta("Amoblamiento");
        compra1Julio2020Amoblamiento.agregarPresupuesto(presupuestoElegido);
        compra1Julio2020Amoblamiento.setPresupuestoElegido(presupuestoElegido);
        return compra1Julio2020Amoblamiento;
    }
    public static Compra getCompra19Julio2020Amoblamiento() {
        Compra compra19Julio2020Amoblamiento = new Compra(repositorioDeMonedas, null, LocalDate.of(2020, 7, 19), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        compra19Julio2020Amoblamiento.agregarItem(item9Con50);
        compra19Julio2020Amoblamiento.agregarEtiqueta("Amoblamiento");
        return compra19Julio2020Amoblamiento;
    }
    public static Compra getCompra12Julio2020SinEtiqueta() {
        Compra compra12Julio2020SinEtiqueta = new Compra(repositorioDeMonedas, null, LocalDate.of(2020, 7, 12), new PagoEnEfectivo(), CodigoMoneda.ARS, 1, new ArrayList<Usuario>());
        compra12Julio2020SinEtiqueta.agregarItem(item9Con50);
        Presupuesto presupuestoElegido = presupuestoConItems(Arrays.asList(item9Con50));
        compra12Julio2020SinEtiqueta.agregarPresupuesto(presupuestoElegido);
        compra12Julio2020SinEtiqueta.setPresupuestoElegido(presupuestoElegido);
        return compra12Julio2020SinEtiqueta;
    }
    public static List<Compra> getComprasConPresupuestoElegido() {
    	List<Compra> compras = new ArrayList<>();
        compras.add(ComprasFactory.getCompraMarzo2018Amoblamiento());
        compras.add(ComprasFactory.getCompraFebrero2017SinEtiqueta());
        compras.add(ComprasFactory.getCompraJulio2020JuanPerez());
        compras.add(ComprasFactory.getCompra1Julio2020Amoblamiento());
        compras.add(ComprasFactory.getCompra19Julio2020Amoblamiento());
        compras.add(ComprasFactory.getCompra12Julio2020SinEtiqueta());
        return compras;
    }

    public static Compra compraConNPresupuestosMinimos(int cantidadMinimaDePresupuestos) {
        EntidadJuridica unaEntidad = new Empresa("Razon SRL", "Kwik-E-Mart", "4517", DireccionesFactory.direccionStub9DeJulio(), 1, new ArrayList<>(), Clasificacion.PEQUENIA);
        LocalDate fechaActual = LocalDate.now();
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(UsuariosFactory.usuarioStub());
        Compra unaCompra = new Compra(new RepositorioDeMonedasMeli(),
                                      unaEntidad,
                                      fechaActual,
                                      new DineroEnCuenta(),
                                      CodigoMoneda.ARS,
                                      cantidadMinimaDePresupuestos, usuarios);
        unaCompra.agregarItem(ItemsFactory.bebida(4, 120));
        return unaCompra;
    }
    public static Compra compraEnEstado(Estado unEstado) {
        Compra unaCompra = compraConNPresupuestosMinimos(1);
        unaCompra.setIndicadorDeAprobacion(unEstado);
        return unaCompra;
    }
    public static Compra compraParaUsuario(Usuario usuario){
        Compra compra = compraEnEstado(Estado.PENDIENTEDEAPROBACION);
        compra.agregarUsuarioValidador(usuario);
        return compra;
    }
    
    private static Presupuesto presupuestoConItems(List<Item> items){
    	return new Presupuesto(items, proveedor);
    }
}
