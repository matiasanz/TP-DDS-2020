package Compra;

public class NoHayPresupuestosSuficientesException extends RuntimeException {
	public NoHayPresupuestosSuficientesException(){
		super("No se ha seleccionado ningun presupuesto");
	}
}
