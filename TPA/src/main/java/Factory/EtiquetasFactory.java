package Factory;

import Proveedor.Proveedor;

public class EtiquetasFactory {
	public static String etiquetaAmoblamiento(){
		return "Amoblamiento";
	}
	public static String etiquetaProveedor(Proveedor unProveedor){
		return unProveedor.getNombre();
	}
}
