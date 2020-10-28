package Main;

import Controladores.HomeController;
import Controladores.UserController;
import spark.ModelAndView;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {

    public static void main(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(8080);
        
        //Muestra en el navegador el stack trace en caso de excepcion no manejada.
        //TODO el dia de la entrega, dejar comentada esta linea
        DebugScreen.enableDebugScreen(); 
        
        Spark.staticFileLocation("/public");

        new Bootstrap().run();

        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        HomeController homeController = new HomeController();
        UserController loginController = new UserController();

        Spark.get("/", (request, response) -> homeController.getHome(), engine);
        
        Spark.post("/login",(request, response) -> loginController.login(request, response));
        
        Spark.get("/menu",(request, response) -> homeController.getUserMenu(request,response));
                
        System.out.println("Servidor iniciado correctamente");
    }
}
