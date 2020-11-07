package Main;

import Controladores.BandejaController;
import Controladores.CompraController;
import Controladores.HomeController;
import Controladores.MenuController;
import Moneda.Moneda;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {
    private static RepositorioDeMonedasMeli repositorioDeMonedasMeli = new RepositorioDeMonedasMeli();
	private static HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
	private static HomeController homeController = new HomeController();
	private static MenuController menuController = new MenuController();
	private static BandejaController bandejaController = new BandejaController();
    private static CompraController compraController = new CompraController();

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

        Spark.get("/", (request, response) -> homeController.getHome(request, response), engine);
        
        Spark.post("/login",(request, response) -> homeController.tryLogin(request, response), engine);
        
        Spark.get("/menu",(request, response) -> menuController.getUserMenu(request,response), engine);
        
        Spark.get("/mensajes",(request, response) -> bandejaController.getBandejaDeMensajes(request,response), engine);
                
        Spark.get("/logout",(request, response)->menuController.logout(request, response), engine);

        Spark.get("/compras/nueva", (request, response) -> compraController.getPaginaComprasNueva(), engine);

        Spark.get("/compras/ver", (request, response) -> compraController.getPaginaVerCompras(request, response), engine);
        
        Spark.get("/compras/ver/:id", (request, response) -> compraController.getPaginaVerCompra(request, response), engine);

        Spark.patch("/compras/:id/etiqueta/nueva", (request, response) -> compraController.agregarEtiqueta(request, response), engine);

        Spark.get("/compras", (request, response) -> compraController.getPaginaComprasMenu(request, response), engine);

        Spark.post("/compras", (request, response) -> compraController.crearCompra(request, response), engine);

        System.out.println("Servidor iniciado correctamente");
    }
}
