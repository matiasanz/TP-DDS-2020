package Controladores;

import Categoria.Categoria;
import Entidad.Entidad;
import Repositorios.RepositorioDeCategorias;
import Repositorios.RepositorioDeEntidades;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntidadesController {
    public ModelAndView getOptions() {
        Map<String, Object> modelo = new HashMap<>();

        return new ModelAndView(modelo, "entidades_base.html.hbs");
    }

    public ModelAndView getCategoriasAElegir(Request request, Response response) {

        String categoriaBuscada = request.queryParams("filtro");
        Map<String, Object> modelo = new HashMap<>();

        List<Categoria> categorias = categoriaBuscada != null?
                RepositorioDeCategorias.instancia.getCategoriasByName(categoriaBuscada) :
                RepositorioDeCategorias.instancia.getCategorias();

        modelo.put("categorias", categorias);

        return new ModelAndView(modelo,"entidades_por_categoria.html.hbs");
    }

    public ModelAndView getEntidadesPorCategoria(Request request, Response response){
        try{
            Map<String, Object> modelo = new HashMap<>();

            Categoria categoria = RepositorioDeCategorias.instancia.findById(Long.parseLong(request.params(":id")));

            if (categoria == null){
                modelo.put("mensajeError", "Error 404 - Categoría no encontrada");
                return new ModelAndView(modelo, "errores.html.hbs");
            }
            List<Entidad> entidades = RepositorioDeEntidades.instancia.listarPorCategoria(Long.parseLong(request.params(":id")));

            modelo.put("categoria", categoria);
            modelo.put("entidades", entidades);

            return new ModelAndView(modelo, "entidades_por_categoria_elegida.html.hbs");

        } catch(NumberFormatException e) {
            return new ModelAndView(devolverBadRequestSiIdTipoErroneo(response), "errores.html.hbs");
        }
    }


    public ModelAndView getEntidadesAAsociar(Request request, Response response) {
        String nombreEntidad = request.queryParams("filtro");
        Map<String, Object> modelo = new HashMap<>();

        List<Entidad> entidades = nombreEntidad != null ?
                RepositorioDeEntidades.instancia.getEntidadesByName(nombreEntidad) :
                RepositorioDeEntidades.instancia.getEntidades();

        modelo.put("entidades", entidades);

        return new ModelAndView(modelo, "entidades_a_asociar.html.hbs");
    }

    public ModelAndView getEntidadYCategorias(Request request, Response response) {

        try{
            Map<String, Object> modelo = new HashMap<>();

            Entidad entidad = RepositorioDeEntidades.instancia.findById(Long.parseLong(request.params(":id")));

            if (entidad == null){
                modelo.put("mensajeError", "Error 404 - Entidad no encontrada");
                return new ModelAndView(modelo, "errores.html.hbs");
            }

            getCategoriasDeEntidad(modelo, entidad);

            return new ModelAndView(modelo, "entidades_a_asociar_elegida.html.hbs");

        } catch(NumberFormatException e) {
            return new ModelAndView(devolverBadRequestSiIdTipoErroneo(response), "errores.html.hbs");
        }
    }

    public ModelAndView agregarCategoriaAEntidad(Request request, Response response) {
        try{
            Map<String, Object> modelo = new HashMap<>();

            Entidad entidad = RepositorioDeEntidades.instancia.findById(Long.parseLong(request.params(":id")));
            Categoria categoria = RepositorioDeCategorias.instancia.findById(Long.parseLong(request.params(":idCategoria")));

            if (entidad == null || categoria == null){
                modelo.put("mensajeError", "Error 404 - Entidad y/o categoría no encontrada");
                return new ModelAndView(modelo, "errores.html.hbs");
            }

            if (!entidad.getCategorias().contains(categoria)){
                entidad.agregarCategoria(categoria);
                RepositorioDeEntidades.instancia.save(entidad);
                modelo.put("mensajeAccion", "La categoría " + categoria.getNombre() + " fue asociada correctamente.");
            }

            getCategoriasDeEntidad(modelo, entidad);

            return new ModelAndView(modelo, "entidades_a_asociar_elegida.html.hbs");

        } catch(NumberFormatException e) {
            return new ModelAndView(devolverBadRequestSiIdTipoErroneo(response), "errores.html.hbs");
        }
    }

    public ModelAndView eliminarCategoriaDeEntidad(Request request, Response response) {
        try{
            Map<String, Object> modelo = new HashMap<>();

            Entidad entidad = RepositorioDeEntidades.instancia.findById(Long.parseLong(request.params(":id")));
            Categoria categoria = RepositorioDeCategorias.instancia.findById(Long.parseLong(request.params(":idCategoria")));

            if (entidad == null || categoria == null){
                modelo.put("mensajeError", "Error 404 - Entidad y/o categoría no encontrada");
                return new ModelAndView(modelo, "errores.html.hbs");
            }

            List<Categoria> categoriasNuevas = entidad.getCategorias();
            categoriasNuevas.remove(categoria);
            entidad.setCategorias(categoriasNuevas);

            RepositorioDeEntidades.instancia.save(entidad);
            modelo.put("mensajeAccion", "La categoría " + categoria.getNombre() + " fue desasociada correctamente.");

            getCategoriasDeEntidad(modelo, entidad);

            return new ModelAndView(modelo, "entidades_a_asociar_elegida.html.hbs");

        } catch(NumberFormatException e) {
            return new ModelAndView(devolverBadRequestSiIdTipoErroneo(response), "errores.html.hbs");
        }
    }

    private void getCategoriasDeEntidad(Map<String, Object> modelo, Entidad entidad) {
        List<Categoria> categoriasAsociadas = RepositorioDeEntidades.instancia.findById(entidad.getId()).getCategorias();
        List<Categoria> categoriasSinAsociar = RepositorioDeCategorias.instancia.getCategorias().
                stream().filter(c -> !categoriasAsociadas.contains(c)).collect(Collectors.toList());

        modelo.put("categoriasAsociadas", categoriasAsociadas);
        modelo.put("categoriasSinAsociar", categoriasSinAsociar);
        modelo.put("idEntidad", entidad.getId());
        modelo.put("entidad", entidad);
    }

    private Map<String, Object> devolverBadRequestSiIdTipoErroneo(Response response) {
        response.status(400);
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("mensajeError", "Error 400 - Tipo de ID incorrecto");
        return modelo;
    }
}
