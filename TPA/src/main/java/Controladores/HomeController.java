package Controladores;

import spark.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HomeController {
    public ModelAndView getHome() {
        Map<String, Object> modelo = new HashMap<>();

        return new ModelAndView(modelo, "index.html.hbs");
    }
}
