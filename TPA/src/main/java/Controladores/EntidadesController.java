package Controladores;

import Categoria.Categoria;
import Entidad.Entidad;
import Repositorios.RepositorioDeCategorias;
import Repositorios.RepositorioDeEntidades;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntidadesController {
    public ModelAndView getOptions() {
        Map<String, Object> modelo = new HashMap<>();

        return new ModelAndView(modelo, "entidades_base.html.hbs");
    }

    public ModelAndView getConsultorasPorCategoria(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();

        String categoriaBuscadaString = request.queryParams("filtro");
        if (categoriaBuscadaString != null) {
            Long id = Long.parseLong(categoriaBuscadaString);
            Categoria categoria = RepositorioDeCategorias.instancia.findById(id);
            List<Entidad> entidades = RepositorioDeEntidades.instancia.listarPorCategoria(id);
            modelo.put("categoria", categoria);
            modelo.put("entidades", entidades);
        }
        return new ModelAndView(modelo,"entidades_por_categoria.html.hbs");
    }
}
