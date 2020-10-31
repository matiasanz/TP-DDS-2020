package Compra;

public class NoHayPresupuestosException extends RuntimeException{
    public NoHayPresupuestosException(){
        super("No hay presupuestos");
    }
}
