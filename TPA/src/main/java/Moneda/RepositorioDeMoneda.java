package Moneda;

import Moneda.CodigoMoneda;
import Moneda.Moneda;

public interface RepositorioDeMoneda {
    Moneda getMoneda(CodigoMoneda codigo);
}
