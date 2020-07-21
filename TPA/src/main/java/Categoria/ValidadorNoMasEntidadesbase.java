package Categoria;

import Entidad.EntidadJuridica;

public class ValidadorNoMasEntidadesbase extends Validador{
    @Override
    void validarBloqueoDeAgregarEntidadesBase(){
        throw new BloqueoDeAgregarEntidadesBaseException();
    }
}
