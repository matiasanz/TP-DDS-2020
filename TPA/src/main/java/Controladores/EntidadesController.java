package Controladores;

import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class EntidadesController {
    public ModelAndView getOptions() {
        Map<String, Object> modelo = new HashMap<>();

        return new ModelAndView(modelo, "entidades_base.html.hbs");
    }
}
