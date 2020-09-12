package Organizacion;
import Direccion.Direccion;
import Direccion.Pais;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMock;
import Moneda.CodigoMoneda;
import Repositorios.RepositorioDeCategorias;
import Repositorios.RepositorioDeEntidades;
import Repositorios.RepositorioDeUsuarios.RepositorioDeUsuarios;

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

import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMock;

public class TestOrganizacion {
    Direccion direccion = new Direccion(new RepositorioDeLocacionesMock(), "Mendoza", 54, 0, "1212", Pais.AR);
	Organizacion organizacion = new Organizacion(new RepositorioDeUsuarios(), new RepositorioDeCategorias(), new RepositorioDeEntidades());
	
	Empresa entidad1 = new Empresa("Arcos Dorados", "McDonalds", "2040495678", direccion, 1234, null, Clasificacion.MICRO);
	OrganizacionSectorSocial entidad2 = new OrganizacionSectorSocial("The Coca Cola Company", "Coca-Cola", "2040495678", direccion, 1234, null);
	Proveedor proveedor1 = Proveedor.PersonaFisica(40495678, 2040495678, "Guido", "Ferrari", null);
	

    @Test (expected = UsuarioYaExisteException.class)
    public void tratoDeCrearDosUsuariosConElMismoUsername() {
    	organizacion.crearUsuario("randomguy", "contraseniagenerica");
        organizacion.crearUsuario("randomguy", "contraseniagenerica2");
    }

    @Test
    public void validoQueSeCreanDosUsuarios() {
    	organizacion.crearUsuario("RonWeasley", "wingardiumleviosa157");
        organizacion.crearUsuario("HarryPotter", "alohomora254");
        
        assertEquals(organizacion.getUsuarios().size(), 2);
    }
    
    @Test
    public void validoTamanioDeListaDeEntidades() {
    	organizacion.agregarEntidad(entidad2);
    	
    	assertEquals(organizacion.getEntidades().size(), 2);
    }
    

    @Before
    public void inits() {
    	
    	Item item1 = new Item("Fotocopiadora", 2, BigDecimal.valueOf(12000));
    	Item item2 = new Item("Escritorios", 3, BigDecimal.valueOf(50400));
    	Item item3 = new Item("Sillas",1, BigDecimal.valueOf(34600));
    	
    	Compra compra1 = new Compra(new RepositorioDeMonedasMock(), entidad1, proveedor1, LocalDate.now() , null, CodigoMoneda.ARS, 3, null);
    	compra1.agregarItem(item1);
    	compra1.agregarItem(item2);
    	
    	Compra compra2 = new Compra(new RepositorioDeMonedasMock(), entidad2, proveedor1, LocalDate.now() , null, CodigoMoneda.ARS, 3, null);
    	compra1.agregarItem(item1);
    	compra1.agregarItem(item3);
    	    	
    	entidad1.agregarCompra(compra1);
    	entidad1.agregarCompra(compra2);
 
    	organizacion.agregarEntidad(entidad1);
    }
    
    @Test
    public void validoTamanioListaDeCompras() {
        assertEquals(organizacion.getCompras().size(), 2);
    }
    
    @Test
    public void validoMontoTotal() {
        assertEquals(BigDecimal.valueOf(233800), organizacion.getValorTodasLasCompras());
    }
    
}