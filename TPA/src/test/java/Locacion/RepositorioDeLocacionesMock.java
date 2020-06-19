package Locacion;

import Proveedor.Pais;
import Repositorios.RepositorioDeLocaciones.*;

public class RepositorioDeLocacionesMock implements RepositorioDeLocaciones {

	@Override
	public Locacion getLocacion(Pais codigoPais, String codigoPostal) {
		return new Locacion(Pais.AR, "CABA", "Caballito");
	}
}