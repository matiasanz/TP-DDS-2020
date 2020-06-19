package Compra;

public class PresupuestoElegidoNoCumpleCriterioException extends RuntimeException {
	public PresupuestoElegidoNoCumpleCriterioException(){
		//Se podria pasar por parametro el criterio y que conozca el mensaje toString() para saber por que fue
		super("El presupuesto elegido no cumple con el criterio seleccionado");
	}
}
