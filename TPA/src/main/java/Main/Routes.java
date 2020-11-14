package Main;

import Controladores.Autenticador;
import Controladores.BandejaController;
import Controladores.CompraController;
import Controladores.EntidadesController;
import Controladores.HomeController;
import Controladores.MenuController;
import spark.Response;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.after;

public class Routes {
	private static final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
	private static final HomeController homeController = new HomeController();
	private static final MenuController menuController = new MenuController();
	private static final BandejaController bandejaController = new BandejaController();
    private static final CompraController compraController = new CompraController();
    private static final EntidadesController entidadesController = new EntidadesController();

    public static void main(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(8080);

        //Esta linea muestra el stack trace en el navegador, en caso de excepcion no manejada.
        //TODO comentar el dia de la entrega
        DebugScreen.enableDebugScreen();

        Spark.staticFileLocation("/public");

        Bootstrap.main(args);
        
        Spark.before((request, response)->{        	        	
        	bloquearCacheNavegador(response);

        	if(!uriExceptuadaDeReautenticar( request.uri() )){
        		Autenticador.instance.reautenticar(request,response);        		
        	}
        });
        
        usuarioRoutes();

        comprasRoutes();
        
        entidadesRoutes();

        after((request, response) -> closeEntityManager() );

        System.out.println("Servidor iniciado correctamente");
    }

    private static void usuarioRoutes(){
        Spark.get("/", homeController::getHome, engine);
    	
    	Spark.post("/login", homeController::tryLogin, engine);

        Spark.get("/menu", menuController::getUserMenu, engine);

        Spark.get("/mensajes", bandejaController::getBandejaDeMensajes, engine);

        Spark.get("/logout", menuController::logout, engine);
    }
    
	private static void comprasRoutes(){

        Spark.get("/compras", compraController::getPaginaComprasMenu, engine);

        Spark.get("/compras/nueva", compraController::getPaginaComprasNueva, engine);

        Spark.post("/compras", compraController::crearCompra, engine);

        Spark.get("/compras/ver", compraController::getPaginaVerCompras, engine);
      
        Spark.get("/compras/ver/:id", compraController::getPaginaVerCompra, engine);

        Spark.post("/compras/:id/etiqueta/nueva", compraController::agregarEtiqueta, engine);

    }

    private static void entidadesRoutes(){
        Spark.get("/entidades", entidadesController::getEntidadesMenu, engine);

        Spark.get("/entidades/categorias", entidadesController::getCategoriasAElegir, engine);

        Spark.get("/entidades/categorias/:id", entidadesController::getEntidadesPorCategoria, engine);

        Spark.get("/entidades/asociar-categoria", entidadesController::getEntidadesAAsociar, engine);

        Spark.get("/entidades/:id/categorias", entidadesController::getEntidadYSusCategorias, engine);

        Spark.post("/entidades/:id/agregar-categoria", entidadesController::agregarCategoriaAEntidad, engine);

        Spark.post("/entidades/:id/eliminar-categoria", entidadesController::eliminarCategoriaDeEntidad, engine);

        Spark.get("/entidades/nueva", entidadesController::getCreadorEntidad, engine);

        Spark.get("/entidades/nueva-base", entidadesController::getCreadorEntidadBase, engine);

        Spark.get("/entidades/nueva-empresa", entidadesController::getCreadorEmpresa, engine);

        Spark.get("/entidades/nueva-org-sector-social", entidadesController::getCreadorOrgSectorSocial, engine);

        Spark.post("/entidades", entidadesController::crearEntidad, engine);
    }
    
    private static void bloquearCacheNavegador(Response respuesta){
		  respuesta.header("Cache-Control", "no-store, must-revalidate");
    }

    private static boolean uriExceptuadaDeReautenticar(String uri){
    	return uri.equals("/main.css") 
    		|| uri.equals("/")
    		|| uri.equals("/login");
    }
    
    private static void closeEntityManager(){
    	PerThreadEntityManagers.getEntityManager();
        PerThreadEntityManagers.closeEntityManager();
    }
}
