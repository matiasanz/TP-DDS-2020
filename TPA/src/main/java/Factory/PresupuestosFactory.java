package Factory;

import Compra.Compra;
import Direccion.Direccion;
import Direccion.Pais;
import Presupuesto.Presupuesto;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocaciones;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMock;

public class PresupuestosFactory {
	public static Presupuesto presupuestoPara(Compra unaCompra) {
		return new Presupuesto(unaCompra.getItems(), ProveedoresFactory.proveedorStub());
	}
}
