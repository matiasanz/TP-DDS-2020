package Factory;

import Direccion.Direccion;
import Direccion.Pais;
import Entidad.Entidad;
import Mocks.RepositorioDeLocacionesMock;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocaciones;

public class VariosFactory
{
	
	public static Direccion direccionStub(){
		RepositorioDeLocaciones repositorioDeLocaciones = new RepositorioDeLocacionesMock();
		
		return new Direccion(repositorioDeLocaciones, "9 de Julio", 15, 1, "1212", Pais.AR);
	}
	
	//Etiquetas
	
	public String etiquetaAmoblamiento(){
		return "Amoblamiento";
	}
	
	public String etiquetaProveedor(Proveedor unProveedor){
		return unProveedor.getNombre();
	}
}
