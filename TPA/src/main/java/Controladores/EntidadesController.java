package Controladores;

import Categoria.Categoria;
import Entidad.Entidad;
import Repositorios.RepositorioDeCategorias;
import Repositorios.RepositorioDeEntidades;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            response.status(400);
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("mensajeError", "Error 400 - Tipo de ID incorrecto");
            return new ModelAndView(modelo, "errores.html.hbs");
        }
    }
}
