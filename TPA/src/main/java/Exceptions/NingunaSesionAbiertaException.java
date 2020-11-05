package Exceptions;

public class NingunaSesionAbiertaException extends RuntimeException
{
	public NingunaSesionAbiertaException(){
		super("Ninguna sesion se encuentra abierta");
	}
}
