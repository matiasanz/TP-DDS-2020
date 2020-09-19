package Factory;

import Proveedor.Proveedor;

public class EtiquetasFactory {
	public String etiquetaAmoblamiento(){
		return "Amoblamiento";
	}
	public String etiquetaProveedor(Proveedor unProveedor){
		return unProveedor.getNombre();
	}
}
