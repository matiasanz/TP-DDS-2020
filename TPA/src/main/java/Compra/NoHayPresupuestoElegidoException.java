package Compra;

public class NoHayPresupuestoElegidoException extends RuntimeException{
    public NoHayPresupuestoElegidoException(){
        super("No hay presupuesto elegido");
    }
}
