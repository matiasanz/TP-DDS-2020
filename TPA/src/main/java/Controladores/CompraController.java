package Controladores;

import Compra.Compra;
import Compra.Item;
import Entidad.Entidad;
import Exceptions.DatosIncompletosException;
import Exceptions.FechaInvalidaException;
import MedioDePago.MedioDePago;
import Modelos.CompraModel;
import Moneda.CodigoMoneda;
import Moneda.Moneda;
import Presupuesto.Presupuesto;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeCompras.RepoComprasDB;
import Repositorios.RepoEntidadesDB;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;
import Usuario.Usuario;
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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompraController extends AbstractPersistenceTest implements WithGlobalEntityManager {

    private final RepoEntidadesDB repositorioEntidades = new RepoEntidadesDB();
    private final RepoComprasDB repositorioCompras = new RepoComprasDB();
    private final RepositorioDeMonedasMeli repositorioDeMonedas = RepositorioDeMonedasMeli.getInstance();
    private final RepositorioDeProveedoresDB repositorioDeProveedores = new RepositorioDeProveedoresDB();
    private final RepositorioMedioDePagoDB repositorioMedioDePago = new RepositorioMedioDePagoDB();

    public ModelAndView getPaginaComprasNueva(Request request, Response response) {
        return inicializarPaginaComprasNueva(new HashMap<>());
    }

    public ModelAndView getPaginaComprasMenu(Request request, Response response) {
        return new ModelAndView(new HashMap<>(), "compras-menu.html.hbs");
    }

    public ModelAndView crearCompra(Request request, Response response) {
        Usuario usuario = Autenticador.instance.getUsuario(request, response);
        Map<String, Object> modelo = new HashMap<>();

        try {
            Compra compra = crearCompra(request);
            withTransaction(() -> repositorioCompras.salvar(compra));
            compra.agregarUsuarioValidador(usuario); //Esto se deberia hacer en otra uri, pero por ahora no lo piden

            response.redirect("/compras/" + compra.getId());
            return new ModelAndView(new HashMap<>(), "/");

        } catch (DatosIncompletosException | FechaInvalidaException e) {
            response.status(HttpURLConnection.HTTP_INTERNAL_ERROR);
            modelo.put("resultado", "Error - " + e.getMessage());
            return inicializarPaginaComprasNueva(modelo);
        }
    }

    public ModelAndView getPaginaVerCompra(Request request, Response response) {
        try {
            Long idCompra = Long.parseLong(request.params("id"));
            return new ModelAndView(new CompraModel(repositorioCompras.getCompra(idCompra)), "compra-individual.html.hbs");
        } catch (Exception e) {
            return new ModelAndView(new HashMap<>(), "compras-menu.html.hbs");
        }
    }

    public ModelAndView getPaginaVerCompras(Request request, Response response) {
        return new ModelAndView(this.crearModeloCompra(), "compras-ver-todas.html.hbs");
    }

    public ModelAndView agregarEtiqueta(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();

        try {
            Long idCompra = Long.parseLong(request.params("id"));
            Compra compra = repositorioCompras.getCompra(idCompra);

            int cantidadEtiquetas = Integer.parseInt(request.queryParams("cantidad_etiquetas"));
            for (int i = 1; i <= cantidadEtiquetas; i++) {
                compra.agregarEtiqueta(request.queryParams("txt_etiqueta_" + i));
            }

            withTransaction(() -> {
                withTransaction(() -> repositorioCompras.salvar(compra));
            });

            modelo.put("resultado", "Etiqueta agregada con éxito!");
            response.redirect("/compras/" + idCompra);
        } catch (Exception e) {
            response.status(HttpURLConnection.HTTP_INTERNAL_ERROR);
            modelo.put("resultado", "Error - " + e.getMessage());
        }

        return inicializarPaginaComprasNueva(modelo);
    }

    public ModelAndView eliminarEtiqueta(Request request, Response response) {

        Map<String, Object> modelo = new HashMap<>();
        Long idCompra = Long.parseLong(request.params("id"));
        try {

            Compra compra = repositorioCompras.getCompra(idCompra);
            compra.eliminarEtiqueta(request.params("etiqueta"));

            withTransaction(() -> {
                withTransaction(() -> repositorioCompras.salvar(compra));
            });

        } catch (Exception e) {
            response.status(HttpURLConnection.HTTP_INTERNAL_ERROR);
            modelo.put("resultado", "Error - " + e.getMessage());
        }

        response.redirect("/compras/" + idCompra);
        return new ModelAndView(new HashMap<>(), "/");
    }

    private ModelAndView inicializarPaginaComprasNueva(Map<String, Object> modelo) {
        modelo.put("proveedores", repositorioDeProveedores.getAll());
        modelo.put("mediosDePago", repositorioMedioDePago.getAll());
        modelo.put("entidades", repositorioEntidades.getAll());

        modelo.put("monedas", repositorioDeMonedas.getMonedas(Moneda.codigosMoneda()));

        return new ModelAndView(modelo, "compras-nueva.html.hbs");
    }

    private Compra crearCompra(Request request) {

        LocalDate fechaOperacion = crearFecha(request.queryParams("txt_fechaOperacion"));

        CodigoMoneda codigoMoneda = CodigoMoneda.valueOf(request.queryParams("ddl_moneda"));
        MedioDePago medioDePago = repositorioMedioDePago.findById(Long.parseLong(request.queryParams("ddl_medio_de_pago")));
        Entidad entidadRelacionada = repositorioEntidades.findById(Long.parseLong(request.queryParams("ddl_entidad")));

        Presupuesto presupuesto = crearPresupuesto(request);

        Compra compra = new Compra(repositorioDeMonedas, entidadRelacionada, fechaOperacion, medioDePago, codigoMoneda, 0);
        compra.agregarPresupuesto(presupuesto);
        compra.setPresupuestoElegido(presupuesto);

        validarCargaDeCompra(compra);

        return compra;
    }

    private LocalDate crearFecha(String fechaIngresada) {
        LocalDate fechaLeida;

        try {
            fechaLeida = LocalDate.parse(fechaIngresada);
        } catch (DateTimeParseException e) {
            throw new FechaInvalidaException(fechaIngresada);
        }

        return fechaLeida;
    }

    private void validarCargaDeCompra(Compra compra) {
        if (compra.getItems().isEmpty()) throw new DatosIncompletosException("items");
        if (compra.getProveedor() == null) throw new DatosIncompletosException("proveedor");
        if (compra.getMedioDePago() == null) throw new DatosIncompletosException("medio de pago");
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

    private HashMap<String, Object> crearModeloCompra() {
        HashMap<String, Object> modelo = new HashMap<>();
        List<CompraModel> compraModel = repositorioCompras.getAll().stream().map(CompraModel::new).collect(Collectors.toList());
        modelo.put("compras", compraModel);
        return modelo;
    }
}
