package Exceptions;

public class DatosIncompletosException extends RuntimeException{
	
	public DatosIncompletosException(String datosOmitidos){
		super("No se cargo " + datosOmitidos);
	}
}
