package Main;

import Controladores.HomeController;
import Controladores.UserController;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {

    public static void main(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(8080);
        
        //TODO el dia de la entrega, dejar comentada esta linea
        //Muestra el stack trace en el navegador, en caso de excepcion no manejada.
        DebugScreen.enableDebugScreen(); 
        
        Spark.staticFileLocation("/public");

        new Bootstrap().run();

        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        HomeController homeController = new HomeController();
        UserController loginController = new UserController();

        Spark.get("/", (request, response) -> homeController.getHome(response), engine);
        
        Spark.post("/login",(request, response) -> loginController.login(request, response), engine);
        
        Spark.get("/menu",(request, response) -> homeController.getUserMenu(request,response), engine);
        
        Spark.get("/mensajes",(request, response) -> homeController.getBandejaDeMensajes(request,response), engine);
                
        System.out.println("Servidor iniciado correctamente");
    }
}
