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
	private static HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
	private static HomeController homeController = new HomeController();
	private static MenuController menuController = new MenuController();
	private static BandejaController bandejaController = new BandejaController();

    public static void main(String[] args) {
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

        comprasRoutes();
        
        System.out.println("Servidor iniciado correctamente");
    }
        
    private static void comprasRoutes(){
        CompraController compraController = new CompraController();
    	
        Spark.get("/compras", compraController::getPaginaComprasMenu, engine);
        
        Spark.get("/compras/nueva", compraController::getPaginaComprasNueva, engine);

        Spark.post("/compras", compraController::crearCompra, engine);

        Spark.get("/compras/ver", compraController::getPaginaVerCompras, engine);

    }
}
