package Repositorios.RepositorioDeMonedas;
import Moneda.Moneda;
import Moneda.CodigoMoneda;

public interface RepositorioDeMonedas {

    Moneda getMoneda(CodigoMoneda codigoMoneda);
}
