package Factory;

import Compra.Compra;
import Presupuesto.Presupuesto;

public class PresupuestosFactory {
	public static Presupuesto presupuestoPara(Compra unaCompra) {
		return new Presupuesto(unaCompra.getItems(), ProveedoresFactory.proveedorStub());
	}
}
