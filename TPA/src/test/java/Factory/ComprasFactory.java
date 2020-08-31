package Factory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import Compra.Compra;
import Compra.Item;
import Entidad.Entidad;
import Entidad.EntidadJuridica;
import MedioDePago.MedioDePago;
import MedioDePago.PagoEnEfectivo;
import Mocks.RepositorioDeMonedasMock;
import Moneda.CodigoMoneda;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeEtiquetas.RepositorioEtiquetas;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;
import Usuario.Usuario;

public class ComprasFactory
{
	RepositorioDeMonedas repositorioDeMonedas = new RepositorioDeMonedasMock();
	
	public static Item itemValuadoEn(double precio){
		return new Item("Item 1", 1, BigDecimal.valueOf(precio));
	}

	public static Item itemNValuadoEn(int cantidad, double precio)
	{
		return new Item("Heladera", cantidad, BigDecimal.valueOf(precio));
	}

	public static Compra compraSinEtiquetas(EntidadJuridica entidad, Proveedor proveedor, MedioDePago medioDePago)
	{
        return new Compra(new RepositorioDeMonedasMock(), new RepositorioEtiquetas(), entidad, proveedor,  LocalDate.now(), medioDePago, CodigoMoneda.ARS, 1, new LinkedList<Usuario>());
	}	
}
