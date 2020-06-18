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

        } catch (Exception e) {
            throw new MonedaNoEncontradaException(codigoMoneda);
        }

        return parseGetMonedaResponse(codigoMoneda, response.getEntity(String.class));
    }

    private  Moneda parseGetMonedaResponse(CodigoMoneda codigoMoneda, String json) {

        ReadContext ctx = JsonPath.parse(json);
        return new Moneda(codigoMoneda, ctx.read("$.description"));
    }
}
