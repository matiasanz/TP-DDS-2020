package Compra;

public class PresupuestoElegidoNoSeEncuentraEntreLosPresupuestosException extends RuntimeException {
	public PresupuestoElegidoNoSeEncuentraEntreLosPresupuestosException(){
		super("El presupuesto elegido no se encuentra entre los preseleccionados para la compra");
	}
}
