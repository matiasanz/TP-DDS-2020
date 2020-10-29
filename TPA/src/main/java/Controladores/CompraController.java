package Controladores;

import Compra.Compra;
import Direccion.Direccion;
import Factory.DireccionesFactory;
import Presupuesto.Presupuesto;
import Proveedor.Proveedor;
import Repositorios.RepoComprasDB;
import Repositorios.RepoEntidadesDB;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMock;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Compra.Item;
import Direccion.Pais;

public class CompraController {

    private RepoEntidadesDB repositorioEntidades = new RepoEntidadesDB();
    private RepoComprasDB repositorioCompras = new RepoComprasDB();
    private Proveedor proveedorFisico = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", DireccionesFactory.direccionStub());
    private Proveedor proveedorJuridico = Proveedor.PersonaJuridica("Una razón social", new Direccion(new RepositorioDeLocacionesMock(), "Una calle", 2, 2, "1874", Pais.AR));


    public ModelAndView getPaginaComra() {

        Map<String, Object> modelo = new HashMap<>();
        List<Proveedor> provedores = new ArrayList<>();
        provedores.add(proveedorFisico);
        provedores.add(proveedorJuridico);
        modelo.put("proveedores", provedores);
        return new ModelAndView(modelo, "cargar-compra.html.hbs");
    }

    public ModelAndView crearCompra(Request request, Response response) {

        Compra compra = _CrearCompra(request);

        try {
            repositorioCompras.agregar(compra);
        } catch (Exception e) {
            response.status(HttpURLConnection.HTTP_INTERNAL_ERROR);

        }
        return null;
    }

    private Compra _CrearCompra(Request request) {
        List<Presupuesto> presupuestos = new ArrayList<>();
        presupuestos.add(_crearPresupuesto(request));


        return null;
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
        //System.out.println("items " + items);
    }
}
