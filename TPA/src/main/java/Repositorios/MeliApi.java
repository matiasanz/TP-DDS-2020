package Repositorios;

import Moneda.CodigoMoneda;
import Direccion.Pais;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;

public class MeliApi {
    private final Client client;
    private static final String API = "https://api.mercadolibre.com/";

    public MeliApi() {
        this.client = Client.create();
    }

    public ClientResponse obtenerMoneda(CodigoMoneda codigoMoneda) {

        String URL = String.format("/currencies/%s", codigoMoneda.toString());
        return  llamarServicio(URL);
    }

    public ClientResponse obtenerLocaciones(Pais codigoPais, String codigoPostal) {

        String URL = String.format("/countries/%s/zip_codes/%s", codigoPais.toString(), codigoPostal);
        return  llamarServicio(URL);
    }

    private void validarRespuesta(ClientResponse response) {

        if (response.getStatus() != HttpURLConnection.HTTP_OK) {
            throw new ConexionApiException();
        }
    }

    private ClientResponse llamarServicio(String path) {

        WebResource recurso = this.client.resource(API).path(path);
        WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);
        ClientResponse response = builder.get(ClientResponse.class);

        validarRespuesta(response);

        return response;
    }
}