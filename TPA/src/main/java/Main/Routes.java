package Main;

import Controladores.BandejaController;
import Controladores.HomeController;
import Controladores.MenuController;
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

        Spark.get("/", (request, response) -> homeController.getHome(request, response), engine);
        
        Spark.post("/login",(request, response) -> homeController.tryLogin(request, response), engine);
        
        Spark.get("/menu",(request, response) -> menuController.getUserMenu(request,response), engine);
        
        Spark.get("/mensajes",(request, response) -> bandejaController.getBandejaDeMensajes(request,response), engine);
                
        Spark.get("/logout",(request, response)->menuController.logout(request, response), engine);
        
        System.out.println("Servidor iniciado correctamente");
    }
}
