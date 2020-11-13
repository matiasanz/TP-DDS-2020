package Exceptions;

import java.time.LocalDate;

public class FechaInvalidaException extends RuntimeException{
	public FechaInvalidaException(String fecha){
		super("La fecha " + fecha + " no es valida");
	}
	
	public FechaInvalidaException(LocalDate fecha){
		new FechaInvalidaException(fecha.toString());
	}
}
