package Categoria;

import Entidad.EntidadJuridica;

public class ValidadorEntidadJuridicaBloqueada extends Validador{

    private EntidadJuridica entidad;

    public ValidadorEntidadJuridicaBloqueada(EntidadJuridica entidad){
        this.entidad = entidad;
    }

    @Override
    void validarSiEntidadJuridicaEstaBloqueada(EntidadJuridica entidad){
        if(this.entidad.equals(entidad)){
            throw new EntidadJuridicaBloqueadaException();
        }
    }
}
