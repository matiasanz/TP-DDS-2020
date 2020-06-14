package Organizacion;
import Moneda.CodigoMoneda;
import org.junit.Before;
import org.junit.Test;

import Compra.Compra;
import Compra.Item;
import Proveedor.Proveedor;
import Entidad.Clasificacion;
import Entidad.Empresa;
import Entidad.OrganizacionSectorSocial;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDate;

public class testOrganizacion {
	Organizacion organizacion = new Organizacion();
	Empresa entidad1 = new Empresa("Arcos Dorados", "McDonalds", "2040495678", "Everywhere", 1234, null, Clasificacion.MICRO);
	OrganizacionSectorSocial entidad2 = new OrganizacionSectorSocial("The Coca Cola Company", "Coca-Cola", "2040495678", "Everywhere", 1234, null);
	Proveedor proveedor1 = new Proveedor(0, 0, null, null, null, null);
	

    @Test (expected = UsuarioYaExisteException.class)
    public void tratoDeCrearDosUsuariosConElMismoUsername() {
    	organizacion.crearUsuario("randomguy", "contraseniagenerica");
        organizacion.crearUsuario("randomguy", "contraseniagenerica2");
    }

    @Test
    public void validoQueSeCreanDosUsuarios() {
    	organizacion.crearUsuario("RonWeasley", "wingardiumleviosa157");
        organizacion.crearUsuario("HarryPotter", "alohomora254");
        
        assertEquals(2, organizacion.getUsuarios().size());
    }
    
    @Test
    public void validoTamanioDeListaDeEntidades() {
    	organizacion.agregarEntidad(entidad1);
    	organizacion.agregarEntidad(entidad2);
    	
    	assertEquals(2, organizacion.getEntidades().size());
    }
    

    @Before
    public void inits() {
    	
    	Item item1 = new Item("Fotocopiadora", 2, BigDecimal.valueOf(12000));
    	Item item2 = new Item("Escritorios", 3, BigDecimal.valueOf(50400));
    	Item item3 = new Item("Sillas",1, BigDecimal.valueOf(34600));
    	
    	Compra compra1 = new Compra(entidad1, proveedor1, LocalDate.now() , null, CodigoMoneda.ARS, 3);
    	compra1.agregarItem(item1);
    	compra1.agregarItem(item2);
    	
    	Compra compra2 = new Compra(entidad2, proveedor1, LocalDate.now() , null, CodigoMoneda.ARS, 3);
    	compra1.agregarItem(item1);
    	compra1.agregarItem(item3);
    	
    	organizacion.agregarCompra(compra1);
    	organizacion.agregarCompra(compra2);

    }
    
    @Test
    public void validoTamanioListaDeCompras() {
        assertEquals(2, organizacion.getCompras().size());
    }
    
    @Test
    public void validoMontoTotal() {
        assertEquals(BigDecimal.valueOf(233800), organizacion.getValorTodasLasCompras());
    }
    
}