package Compra;

public enum Estado {
    APROBADA, 
    RECHAZADA, PENDIENTEDEAPROBACION{
    	@Override
    	public String toString() {
    		return "PENDIENTE DE APROBACION";
    	}
    }
}

