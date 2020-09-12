package Factory;

import Direccion.Direccion;
import Direccion.Pais;
import Repositorios.RepositorioDeLocaciones.*;

public class DireccionesFactory {
	public static Direccion direccionStub(){
		RepositorioDeLocaciones repositorioDeLocaciones = new RepositorioDeLocacionesMock();
		return new Direccion(repositorioDeLocaciones, "9 de Julio", 15, 1, "1212", Pais.AR);
	}
}
