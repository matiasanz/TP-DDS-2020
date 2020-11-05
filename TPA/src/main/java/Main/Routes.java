package Main;

import Controladores.BandejaController;
import Controladores.CompraController;
import Controladores.EntidadesController;
import Controladores.HomeController;
import Controladores.MenuController;
import Moneda.Moneda;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.after;

public class Routes {
    private static final RepositorioDeMonedasMeli repositorioDeMonedasMeli = new RepositorioDeMonedasMeli();
	private static final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
	private static final HomeController homeController = new HomeController();
	private static final MenuController menuController = new MenuController();
	private static final BandejaController bandejaController = new BandejaController();
    private static final CompraController compraController = new CompraController();
    private static final EntidadesController entidadesController = new EntidadesController();

    public static void main(String[] args) {
        //Cargamos la cache
        repositorioDeMonedasMeli.getMonedas(Moneda.codigosMoneda());
        System.out.println("Iniciando servidor");

        Spark.port(8080);

        //Esta linea muestra el stack trace en el navegador, en caso de excepcion no manejada.
        //TODO comentar el dia de la entrega
        DebugScreen.enableDebugScreen();

        Spark.staticFileLocation("/public");

        new Bootstrap().run();

        Spark.get("/", homeController::getHome, engine);

        Spark.post("/login", homeController::tryLogin, engine);

        Spark.get("/menu", menuController::getUserMenu, engine);

        Spark.get("/mensajes", bandejaController::getBandejaDeMensajes, engine);

        Spark.get("/logout", menuController::logout, engine);

        Spark.get("/compras/nueva", (request, response) -> compraController.getPaginaComrasNueva(), engine);

        Spark.get("/compras/ver", compraController::getPaginaVerCompras, engine);

        Spark.get("/compras", compraController::getPaginaComprasMenu, engine);

        Spark.post("/compras", compraController::crearCompra, engine);

        Spark.get("/entidades", (request, response) -> entidadesController.getOptions(), engine);

        Spark.get("/entidades/categorias", entidadesController::getCategoriasAElegir, engine);

        Spark.get("/entidades/categorias/:id", entidadesController::getEntidadesPorCategoria, engine);

        Spark.get("/entidades/asociarCategoria", entidadesController::getEntidadesAAsociar, engine);

        Spark.get("/entidades/:id/categorias", entidadesController::getEntidadYCategorias, engine);

        Spark.post("/entidades/:id/agregarCategoria", entidadesController::agregarCategoriaAEntidad, engine);

        Spark.post("/entidades/:id/eliminarCategoria", entidadesController::eliminarCategoriaDeEntidad, engine);

        Spark.get("/entidades/nueva", (request, response) -> entidadesController.getCreadorDeEntidad(), engine);

        //Spark.post("/entidades", entidadesController::crearEntidad, engine);

        after((request, response) -> {
            PerThreadEntityManagers.getEntityManager();
            PerThreadEntityManagers.closeEntityManager();
        });
        System.out.println("Servidor iniciado correctamente");
    }
}
