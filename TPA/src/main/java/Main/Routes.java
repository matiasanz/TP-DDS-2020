package Main;

import Controladores.CompraController;
import Controladores.HomeController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {

    public static void main(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(8080);
        Spark.staticFileLocation("/public");
        Spark.staticFileLocation("/js");

        new Bootstrap().run();

        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
        HomeController homeController = new HomeController();

        Spark.get("/", (request, response) -> homeController.getHome(), engine);

        CompraController compraController = new CompraController();
        Spark.get("/compras/nueva", (request, response) -> compraController.getPaginaComra(), engine);
        Spark.post("/compras", (request, response) -> compraController.crearCompra(request, response), engine);
    }
}
