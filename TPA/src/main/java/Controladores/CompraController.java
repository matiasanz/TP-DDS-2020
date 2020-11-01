package Controladores;

import Compra.Compra;
import Compra.Item;
import Entidad.Entidad;
import MedioDePago.MedioDePago;
import Moneda.CodigoMoneda;
import Moneda.Moneda;
import Presupuesto.Presupuesto;
import Proveedor.Proveedor;
import Repositorios.RepoComprasDB;
import Repositorios.RepoEntidadesDB;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;
import Repositorios.RepositorioDeProveedoresDB;
import Repositorios.RepositorioMedioDePagoDB;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompraController extends AbstractPersistenceTest implements WithGlobalEntityManager {

    private final RepoEntidadesDB repositorioEntidades = new RepoEntidadesDB();
    private final RepoComprasDB repositorioCompras = new RepoComprasDB();
    private final RepositorioDeMonedasMeli repositorioDeMonedas = new RepositorioDeMonedasMeli();
    private final RepositorioDeProveedoresDB repositorioDeProveedores = new RepositorioDeProveedoresDB();
    private final RepositorioMedioDePagoDB repositorioMedioDePago = new RepositorioMedioDePagoDB();

    public ModelAndView getPaginaComrasNueva() {
        return inicializarPaginaComprasNueva(new HashMap<>());
    }

    public ModelAndView getPaginaComprasMenu(Request request, Response response) {
        return new ModelAndView(new HashMap<>(), "compras-menu.html.hbs");
    }

    public ModelAndView crearCompra(Request request, Response response) {

        Map<String, Object> modelo = new HashMap<>();

        try {
            Compra compra = crearCompra(request);
            withTransaction(() -> repositorioCompras.agregar(compra));
            //TO-DO: redireccionar a /compra/:id al salvar (Agus)
            modelo.put("resultado", "Compra salvada con exito!");
        } catch (Exception e) {
            response.status(HttpURLConnection.HTTP_INTERNAL_ERROR);
            modelo.put("resultado", "Error - " + e.getMessage());
        }

        return inicializarPaginaComprasNueva(modelo);
    }

    public ModelAndView getPaginaVerCompras(Request request, Response response) {
        return new ModelAndView(new HashMap<>(), "compras-ver-todas.html.hbs");
    }

    private ModelAndView inicializarPaginaComprasNueva(Map<String, Object> modelo) {
        modelo.put("proveedores", repositorioDeProveedores.getAll());
        modelo.put("mediosDePago", repositorioMedioDePago.getAll());
        modelo.put("entidades", repositorioEntidades.getAll());

        modelo.put("monedas", repositorioDeMonedas.getMonedas(Moneda.codigosMoneda()));

        return new ModelAndView(modelo, "compras-nueva.html.hbs");
    }

    private Compra crearCompra(Request request) {

        LocalDate fechaOperacion = LocalDate.parse(request.queryParams("txt_fechaOperacion"));
        CodigoMoneda codigoMoneda = CodigoMoneda.valueOf(request.queryParams("ddl_moneda"));
        MedioDePago medioDePago = repositorioMedioDePago.findById(Long.parseLong(request.queryParams("ddl_medio_de_pago")));
        Entidad entidadRelacionada = repositorioEntidades.findById(Long.parseLong(request.queryParams("ddl_entidad")));

        Presupuesto presupuesto = crearPresupuesto(request);

        Compra compra = new Compra(repositorioDeMonedas, entidadRelacionada, fechaOperacion, medioDePago, codigoMoneda, 0, null);
        compra.agregarPresupuesto(presupuesto);
        compra.setPresupuestoElegido(presupuesto);

        compra.validar();

        return compra;
    }

    private Presupuesto crearPresupuesto(Request request) {

        List<Item> items = new ArrayList<>();
        int cantidadItems = Integer.parseInt(request.queryParams("cantidad_items"));

        for (int i = 1; i <= cantidadItems; i++) {
            int cantidad = Integer.parseInt(request.queryParams("txt_cantidad_" + i));
            String descripcion = request.queryParams("txt_descripcion_" + i);
            BigDecimal valorUnitario = new BigDecimal(request.queryParams("txt_valor_" + i));

            Item item = new Item(descripcion, cantidad, valorUnitario);
            items.add(item);
        }

        Proveedor proveedor = repositorioDeProveedores.findById(Long.parseLong(request.queryParams("ddl_proveedores")));
        return new Presupuesto(items, proveedor);
    }

}
