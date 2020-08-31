package Factory;

import Direccion.Direccion;
import Direccion.Pais;
import Mocks.RepositorioDeLocacionesMock;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocaciones;

public class VariosFactory
{
	
	public static Direccion direccionStub(){
		RepositorioDeLocaciones repositorioDeLocaciones = new RepositorioDeLocacionesMock();
		
		return new Direccion(repositorioDeLocaciones, "9 de Julio", 15, 1, "1212", Pais.AR);
	}
}
