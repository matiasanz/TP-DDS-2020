package Controladores;

import Compra.Compra;
import Direccion.Direccion;
import Factory.DireccionesFactory;
import Moneda.CodigoMoneda;
import Presupuesto.Presupuesto;
import Proveedor.Proveedor;
import Repositorios.RepoComprasDB;
import Repositorios.RepoEntidadesDB;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMock;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;
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

import Compra.Item;
import Direccion.Pais;

public class CompraController {

    private final RepoEntidadesDB repositorioEntidades = new RepoEntidadesDB();
    private final RepoComprasDB repositorioCompras = new RepoComprasDB();
    private final RepositorioDeMonedas repositorioDeMonedas = new RepositorioDeMonedasMeli();

    private final Proveedor proveedorFisico = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", DireccionesFactory.direccionStub());
    private final Proveedor proveedorJuridico = Proveedor.PersonaJuridica("Una razón social", new Direccion(new RepositorioDeLocacionesMock(), "Una calle", 2, 2, "1874", Pais.AR));

    private ModelAndView _inicializarPagina(Map<String, Object> modelo) {
        List<Proveedor> provedores = new ArrayList<>();
        provedores.add(proveedorFisico);
        provedores.add(proveedorJuridico);
        modelo.put("proveedores", provedores);
        return new ModelAndView(modelo, "cargar-compra.html.hbs");
    }

    public ModelAndView getPaginaComra() {
        return _inicializarPagina(new HashMap<>());
    }

    public ModelAndView crearCompra(Request request, Response response) {

        Map<String, Object> modelo = new HashMap<>();

        try {
            Compra compra = _CrearCompra(request);
            repositorioCompras.agregarEnTransaccion(compra);
            modelo.put("resultado", "Compra salvada con exito!");
        } catch (Exception e) {
            response.status(HttpURLConnection.HTTP_INTERNAL_ERROR);
            modelo.put("resultado", "Error - " + e.getMessage());
        }

        return _inicializarPagina(modelo);
    }

    private Compra _CrearCompra(Request request) {

        LocalDate fechaOperacion = LocalDate.parse(request.queryParams("txt_fechaOperacion"));
        CodigoMoneda codigoMoneda = CodigoMoneda.valueOf(request.queryParams("txt_moneda"));

        Presupuesto presupuesto = _crearPresupuesto(request);

        Compra compra = new Compra(repositorioDeMonedas, null, fechaOperacion, null, codigoMoneda, 0, null);
        compra.agregarPresupuesto(presupuesto);
        compra.setPresupuestoElegido(presupuesto);

        compra.validar();

        return compra;
    }

    private Presupuesto _crearPresupuesto(Request request) {

        List<Item> items = new ArrayList<>();
        int cantidadItems = Integer.parseInt(request.queryParams("cantidad_items"));

        for (int i = 1; i <= cantidadItems; i++) {
            int cantidad = Integer.parseInt(request.queryParams("txt_cantidad_" + i));
            String descripcion = request.queryParams("txt_descripcion_" + i);
            BigDecimal valorUnitario = new BigDecimal(request.queryParams("txt_valor_" + i));

            Item item = new Item(descripcion, cantidad, valorUnitario);
            items.add(item);
        }

        return new Presupuesto(items, proveedorFisico);
    }
}
