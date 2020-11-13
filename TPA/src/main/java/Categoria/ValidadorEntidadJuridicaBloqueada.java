package Categoria;

import Entidad.EntidadJuridica;
import Exceptions.EntidadJuridicaBloqueadaException;

import javax.persistence.*;

@Entity
@DiscriminatorValue("EJB")
public class ValidadorEntidadJuridicaBloqueada extends Validador{

    @ManyToOne
    @JoinColumn(name = "entidad")
    private EntidadJuridica entidad;
    
    public ValidadorEntidadJuridicaBloqueada() {
    	
    }

    public ValidadorEntidadJuridicaBloqueada(EntidadJuridica entidad){
        this.entidad = entidad;
    }

    @Override
    void validarSiEntidadJuridicaEstaBloqueada(EntidadJuridica entidad){
        if(this.entidad.equals(entidad)){
            throw new EntidadJuridicaBloqueadaException();
        }
    }

	public EntidadJuridica getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadJuridica entidad) {
		this.entidad = entidad;
	}
}
