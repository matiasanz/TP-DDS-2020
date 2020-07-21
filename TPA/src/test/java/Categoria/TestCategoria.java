package Categoria;

import Compra.Compra;
import Compra.Item;
import Direccion.Direccion;
import Direccion.Pais;
import Entidad.Clasificacion;
import Entidad.Empresa;
import Entidad.EntidadBase;
import Entidad.EntidadJuridica;
import MedioDePago.MedioDePago;
import MedioDePago.PagoEnEfectivo;
import Mocks.RepositorioDeLocacionesMock;
import Mocks.RepositorioDeMonedasMock;
import Moneda.CodigoMoneda;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocaciones;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class TestCategoria {
    RepositorioDeLocaciones repositorioDeLocaciones;
    RepositorioDeMonedas repositorioDeMonedas;
    Direccion direccion;
    EntidadJuridica entidadJuridica;
    EntidadBase entidadBase;
    Categoria categoria;
    Item item;
    Compra compra;
    Proveedor proveedor;
    MedioDePago medioDePago;

    @Before
    public void init() {
        repositorioDeLocaciones = new RepositorioDeLocacionesMock();
        repositorioDeMonedas = new RepositorioDeMonedasMock();

        direccion = new Direccion(repositorioDeLocaciones, "9 de Julio", 15, 1, "1212", Pais.AR);
        entidadJuridica = new Empresa("ArcosDorados S.A.", "McDonald's", "123456789", direccion, 1, null, Clasificacion.MEDIANATRAMO2);
        entidadBase = new EntidadBase("Entidad Base Random", "Es una entidad base random para el test");
        categoria = new Categoria("ONG");

        proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccion);
        medioDePago = new PagoEnEfectivo();
        item = new Item("Heladera", 4, BigDecimal.valueOf(40));
        Compra compra = new Compra(repositorioDeMonedas, entidadJuridica, proveedor, LocalDate.now(), medioDePago, CodigoMoneda.ARS, 1, null);
        compra.agregarItem(item);
    }

    @Ignore
    @Test
    public void categoriaMePermiteAgregarOQuitarComportamiento(){
        Validador validador1 = new ValidadorMontoMaximo(BigDecimal.valueOf(50));
        Validador validador2 = new ValidadorNoMasEntidadesbase();

        categoria.agregarValidador(validador1);
        categoria.agregarValidador(validador2);
        categoria.eliminarValidador(validador1);

        assertEquals(1, categoria.getValidadores().size());
    }

    @Ignore
    @Test(expected = BloqueoDeAgregarEntidadesBaseException.class)
    public void categoriaMePermiteAgregarCompraPeroNoAgregarEntidadBase() {
        categoria.agregarValidador(new ValidadorMontoMaximo(BigDecimal.valueOf(170)));
        categoria.agregarValidador(new ValidadorNoMasEntidadesbase());
        entidadJuridica.agregarCategoria(categoria);

        entidadJuridica.agregarCompra(compra);
        entidadJuridica.agregarEntidadBase(entidadBase);
    }

    @Ignore
    @Test(expected = MontoMaximoExcedidoException.class)
    public void categoriaNoMePermiteAgregarCompraYaQueExcediElMontoMaximo(){
        categoria.agregarValidador(new ValidadorMontoMaximo(BigDecimal.valueOf(150)));
        entidadJuridica.agregarCategoria(categoria);
        entidadJuridica.agregarCompra(compra);
    }

    @Ignore
    @Test(expected = EntidadJuridicaBloqueadaException.class)
    public void CategoriaNoLePermiteALaBaseSerAgregadaAUnaJuridica(){
        categoria.agregarValidador(new ValidadorEntidadJuridicaBloqueada(entidadJuridica));
        entidadBase.agregarCategoria(categoria);
        entidadJuridica.agregarEntidadBase(entidadBase);
    }
}
