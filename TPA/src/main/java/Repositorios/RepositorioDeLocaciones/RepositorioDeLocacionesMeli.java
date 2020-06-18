package Repositorios.RepositorioDeLocaciones;

import Proveedor.Pais;
import Repositorios.MeliApi;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.sun.jersey.api.client.ClientResponse;

public class RepositorioDeLocacionesMeli implements RepositorioDeLocaciones {

    private final MeliApi meliApi;

    public RepositorioDeLocacionesMeli() {
        this.meliApi = new MeliApi();
    }

    @Override
    public Locacion getLocacion(Pais codigoPais, String codigoPostal) {

        ClientResponse response;

        try {
            response = meliApi.obtenerLocaciones(codigoPais, codigoPostal);
        } catch (Exception e) {
            throw new LocacionNoEncontradaException(codigoPais);
        }

        return parseGetLocationResponse(codigoPais, response.getEntity(String.class));
    }

    private Locacion parseGetLocationResponse(Pais codigoPais, String json) {

        ReadContext ctx = JsonPath.parse(json);
        return new Locacion(codigoPais, ctx.read("$.state.name"), ctx.read("$.city.name"));
    }
}
