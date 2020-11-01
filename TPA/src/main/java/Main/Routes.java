package Main;

import Controladores.EntidadesController;
import Controladores.HomeController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {

    public static void main(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(8080);
        Spark.staticFileLocation("/public");

        new Bootstrap().run();

        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        HomeController homeController = new HomeController();
        EntidadesController entidadesController = new EntidadesController();

        Spark.get("/", (request, response) -> homeController.getHome(), engine);
        Spark.get("/entidades", (request, response) -> entidadesController.getOptions(), engine);
        Spark.get("/entidades/porCategoria", entidadesController::getCategoriasAElegir, engine);
        Spark.get("/entidades/porCategoria/:id", entidadesController::getEntidadesPorCategoria, engine);
    }
}
