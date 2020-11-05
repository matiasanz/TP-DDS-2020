package Organizacion;

public class IngresoFallidoException extends RuntimeException{
	public IngresoFallidoException(){
		super("El usuario y/o la contraseña ingresada son incorrectos");
	}
}