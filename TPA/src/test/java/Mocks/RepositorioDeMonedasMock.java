package Mocks;

import Moneda.Moneda;
import Moneda.CodigoMoneda;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;

public class RepositorioDeMonedasMock implements RepositorioDeMonedas {

	@Override
    public Moneda getMoneda(CodigoMoneda codigoMoneda) {
        return new Moneda(CodigoMoneda.ARS, "Peso Argentino");
    }
}