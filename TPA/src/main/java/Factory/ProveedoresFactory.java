package Factory;

import Direccion.Direccion;
import Proveedor.Proveedor;

public class ProveedoresFactory {
	public static Proveedor proveedorStub() {
		Direccion unaDireccion = DireccionesFactory.direccionStub();
		return Proveedor.PersonaFisica(1, 1, "Juan", "Salvo", unaDireccion);
	}
}
