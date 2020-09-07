package Factory;

import java.util.ArrayList;
import Direccion.Direccion;
import Entidad.Clasificacion;
import Entidad.Empresa;
import Entidad.Entidad;
import Entidad.EntidadBase;
import Entidad.EntidadJuridica;
import Entidad.OrganizacionSectorSocial;
import Fabrica.Fabrica;
import Mocks.RepositorioDeMonedasMock;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;

public class EntidadesFactory{
	static Direccion direccionStub = VariosFactory.direccionStub();
	static RepositorioDeMonedas repositorioDeMonedas = new RepositorioDeMonedasMock();
	
	public static EntidadBase baseRandom(){
		return new EntidadBase("Entidad Base Random", "Es una entidad base random para el test");
	}

	public static EntidadJuridica empresaMedianaTramo2(){
		return new Empresa("ArcosDorados S.A.", "McDonald's", "123456789", direccionStub, 1, null, Clasificacion.MEDIANATRAMO2);
	}

	public static Proveedor personaHumana(){
		return Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccionStub);
	}
	
	public static Entidad getEntidadStub(){
		return new OrganizacionSectorSocial("Entidad de Prueba", "Entidad Real", "1222222224", Fabrica.direccionStub(), 845, new ArrayList<>());
	}
	    
    public static Entidad getEntidadConCompras() {
        Entidad entidad = EntidadesFactory.getEntidadStub();
        ComprasFactory.getComprasConPresupuestoElegido().forEach(compra -> entidad.agregarCompra(compra));
        return entidad;
    }
}