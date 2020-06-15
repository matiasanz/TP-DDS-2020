package Repositorios.RepositorioDeMonedas;

import Moneda.CodigoMoneda;
import Moneda.Moneda;
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

            //Moneda Invalida
            if (!response.hasEntity())
                return null;

        } catch (Exception e) {
            return null;
        }

        return parseGetmonedaResponse(codigoMoneda, response.getEntity(String.class));
    }

    private  Moneda parseGetmonedaResponse(CodigoMoneda codigoMoneda, String json) {

        ReadContext ctx = JsonPath.parse(json);
        return new Moneda(codigoMoneda, ctx.read("$.description"));
    }
}
