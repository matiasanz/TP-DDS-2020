package Factory;

import Direccion.Direccion;
import Proveedor.Proveedor;

public class ProveedoresFactory {
	public static Proveedor proveedorStub() {
		return Proveedor.PersonaFisica(1, 1, "Juan", "Salvo", DireccionesFactory.direccionStub9DeJulio());
	}

	public static Proveedor ProveedorMaster() {
		return Proveedor.PersonaJuridica("Master Provider SA", DireccionesFactory.direccionStubCervantes());
	}

	public static Proveedor ProveedorOne() {
		return Proveedor.PersonaJuridica("Proveedor One SRL", DireccionesFactory.direccionStubCordoba());
	}

	public static Proveedor ProveedorJuanPerez() {
		return Proveedor.PersonaFisica(34321047, 1334321047, "Juan", "Perez", DireccionesFactory.direccionStub9DeJulio());
	}
}
