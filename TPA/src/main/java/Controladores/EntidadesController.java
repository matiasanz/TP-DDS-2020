package Controladores;

import Categoria.Categoria;
import Entidad.Entidad;
import Repositorios.RepositorioDeCategorias;
import Repositorios.RepositorioDeEntidades;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntidadesController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

    private final Autenticador autenticador = Autenticador.getInstance();

    public ModelAndView getEntidadesMenu(Request request, Response response) {
        autenticador.reconocerUsuario(request, response);

        Map<String, Object> modelo = new HashMap<>();
        return new ModelAndView(modelo, "entidades_base.html.hbs");
    }

    public ModelAndView getCategoriasAElegir(Request request, Response response) {
        autenticador.reconocerUsuario(request, response);

        String categoriaBuscada = request.queryParams("filtro");
        Map<String, Object> modelo = new HashMap<>();

        List<Categoria> categorias = categoriaBuscada != null?
                RepositorioDeCategorias.instancia.getCategoriasByName(categoriaBuscada) :
                RepositorioDeCategorias.instancia.getCategorias();

        modelo.put("categorias", categorias);

        return new ModelAndView(modelo,"entidades_por_categoria.html.hbs");
    }


    public ModelAndView getCreadorDeEntidad(Request request, Response response) {
        //autenticador.reconocerUsuario(request, response);

        List<Categoria> categoriasDisponibles = RepositorioDeCategorias.instancia.getCategorias();

        /* TODO: A la hora de crear la entidad:
        - Se elige el tipo de entidad -> A raíz de esto nacen tres formularios distintos
        - Permite elegir los valores y en el caso de la categoría debe permitir ir a un ABM para crear
         */

        return new ModelAndView(new HashMap<>(), "entidades_nueva.html.hbs");
    }

    public ModelAndView getEntidadesPorCategoria(Request request, Response response){
        autenticador.reconocerUsuario(request, response);

        try{
            Map<String, Object> modelo = new HashMap<>();

            Categoria categoria = RepositorioDeCategorias.instancia.findById(Long.parseLong(request.params(":id")));

            if (categoria == null){
                return returnError404(response);
            }

            List<Entidad> entidades = RepositorioDeEntidades.instancia.listarPorCategoria(Long.parseLong(request.params(":id")));

            response.status(200);
            modelo.put("categoria", categoria);
            modelo.put("entidades", entidades);

            return new ModelAndView(modelo, "entidades_por_categoria_elegida.html.hbs");

        } catch(NumberFormatException e) {
            return returnError400(response);
        }
    }

    public ModelAndView getEntidadesAAsociar(Request request, Response response) {
        autenticador.reconocerUsuario(request, response);

        String nombreEntidad = request.queryParams("filtro");
        Map<String, Object> modelo = new HashMap<>();

        List<Entidad> entidades = nombreEntidad != null ?
                RepositorioDeEntidades.instancia.getEntidadesByName(nombreEntidad) :
                RepositorioDeEntidades.instancia.getEntidades();

        modelo.put("entidades", entidades);

        return new ModelAndView(modelo, "entidades_a_asociar.html.hbs");
    }

    public ModelAndView getEntidadYSusCategorias(Request request, Response response) {
        autenticador.reconocerUsuario(request, response);

        try{
            Map<String, Object> modelo = new HashMap<>();

            Entidad entidad = RepositorioDeEntidades.instancia.findById(Long.parseLong(request.params(":id")));

            if (entidad == null){
                return returnError404(response);
            }

            return getModelConCategoriasAsociadasYSinAsociar(modelo, entidad);

        } catch(NumberFormatException e) {
            return returnError400(response);
        }
    }

    public ModelAndView agregarCategoriaAEntidad(Request request, Response response) {
        autenticador.reconocerUsuario(request, response);

        try{
            Entidad entidad = RepositorioDeEntidades.instancia.findById(Long.parseLong(request.params(":id")));
            Categoria categoria = RepositorioDeCategorias.instancia.findById(Long.parseLong(request.queryParams("idCategoria")));

            if (entidad == null || categoria == null){
                return returnError404(response);
            }

            Map<String, Object> modelo = new HashMap<>();

            if (!entidad.getCategorias().contains(categoria)){
                withTransaction(() -> {
                    entidad.agregarCategoria(categoria);
                    RepositorioDeEntidades.instancia.save(entidad);
                } );
                modelo.put("mensajeAccion", "La categoría " + categoria.getNombre() + " fue asociada correctamente.");
            }

            return getModelConCategoriasAsociadasYSinAsociar(modelo, entidad);

        } catch(NumberFormatException e) {
            return returnError400(response);
        }
    }

    public ModelAndView eliminarCategoriaDeEntidad(Request request, Response response) {
        autenticador.reconocerUsuario(request, response);

        try{
            Entidad entidad = RepositorioDeEntidades.instancia.findById(Long.parseLong(request.params(":id")));
            Categoria categoria = RepositorioDeCategorias.instancia.findById(Long.parseLong(request.queryParams("idCategoria")));

            if (entidad == null || categoria == null){
                return returnError404(response);
            }

            withTransaction(() -> {
                entidad.eliminarCategoria(categoria);
                RepositorioDeEntidades.instancia.save(entidad);
            } );

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("mensajeAccion", "La categoría " + categoria.getNombre() + " fue desasociada correctamente.");

            return getModelConCategoriasAsociadasYSinAsociar(modelo, entidad);

        } catch(NumberFormatException e) {
            return returnError400(response);
        }
    }

    private ModelAndView returnError400(Response response) {
        return returnError(response, 400, "Error 400 - Tipo de ID incorrecto");
    }

    private ModelAndView returnError404(Response response) {
        return returnError(response, 404, "Error 404 - Entidad y/o categoría no encontrada");
    }

    private ModelAndView returnError(Response response, int status, String mensaje){
        Map<String, Object> modelo = new HashMap<>();
        response.status(status);
        modelo.put("mensajeError", mensaje);
        return new ModelAndView(modelo, "errores.html.hbs");
    }

    private ModelAndView getModelConCategoriasAsociadasYSinAsociar(Map<String, Object> modelo, Entidad entidad) {
        List<Categoria> categoriasAsociadas = entidad.getCategorias();
        List<Categoria> categoriasSinAsociar = RepositorioDeCategorias.instancia.getCategorias().
                stream().filter(c -> !categoriasAsociadas.contains(c)).collect(Collectors.toList());

        modelo.put("categoriasAsociadas", categoriasAsociadas);
        modelo.put("categoriasSinAsociar", categoriasSinAsociar);
        modelo.put("idEntidad", entidad.getId());
        modelo.put("entidad", entidad);
        return new ModelAndView(modelo, "entidades_a_asociar_elegida.html.hbs");
    }
}
