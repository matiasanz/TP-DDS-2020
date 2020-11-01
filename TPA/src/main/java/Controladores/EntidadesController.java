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
            Categoria categoria = RepositorioDeCategorias.instancia.findById(Long.parseLong(request.params(":id")));
            List<Entidad> entidades = RepositorioDeEntidades.instancia.listarPorCategoria(Long.parseLong(request.params(":id")));

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("categoria", categoria);
            modelo.put("entidades", entidades);

            return new ModelAndView(modelo, "entidades_por_categoria_elegida.html.hbs");

            //TODO CREAR ESTE HTML QUE MUESTRA LA INFO DE LA CATEGORIA Y DEBAJO UNA LISTA DE LAS ENTIDADES

        } catch(NumberFormatException e) {
            response.status(400);
            //return "Bad Request";
            return new ModelAndView(new HashMap<>(), "entidades_por_categoria_elegida.html.hbs");
        }
    }
}
