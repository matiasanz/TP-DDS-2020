package Categoria;

import Entidad.Entidad;

public abstract class Categoria {

    //TODO todavía no sabemos bien como encararlo
    public void notificarCompraAgregada(Entidad entidad){}
    public void notificarEntidadBaseAgregada(Entidad entidad, Entidad entidadBase){}

    public void validarMontoMaximo(Entidad entidad, int monto){
    }
}
