package Repositorios;

public class ConexionApiException extends RuntimeException {
    public ConexionApiException() {
        super("Error al contactar a la API de Mercado Libre");
    }
}