package Compra;

public enum Estado {
    APROBADA{
    	@Override
    	public String toString() {
    		
    		return "Aprobada";
    	}
    }, 
    RECHAZADA{
    	@Override
    	public String toString() {
    		
    		return "Rechazada";
    	}
    }
    ,PENDIENTEDEAPROBACION{
    	@Override
    	public String toString() {
    		return "Pendiente de Aprobacion";
    	}
    }
}

