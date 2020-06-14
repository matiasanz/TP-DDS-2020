package Repositorios.RepositorioDeMonedas;

import Repositorios.CodigoMoneda;
import Repositorios.MeliApi;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.sun.jersey.api.client.ClientResponse;

public class RepositorioDeMonedasMeli implements RepositorioDeMonedas {

    private final MeliApi meliApi;

    public RepositorioDeMonedasMeli() {
        this.meliApi = new MeliApi();
    }

    @Override
    public Moneda getMoneda(CodigoMoneda codigoMoneda) {

        ClientResponse response;

        try {
            response = meliApi.obtenerMoneda(codigoMoneda);

            //Moneda Inválida
            if (!response.hasEntity())
                return null;

        } catch (Exception e) {
            return null;
        }

        return new Moneda(codigoMoneda, parseGetmonedaResponse(response.getEntity(String.class)));
    }

    private String parseGetmonedaResponse(String json) {
        ReadContext ctx = JsonPath.parse(json);
        return ctx.read("$.description");
    }
}
