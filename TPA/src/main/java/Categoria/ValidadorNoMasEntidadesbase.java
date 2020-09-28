package Categoria;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("NEB")
public class ValidadorNoMasEntidadesbase extends Validador{

    @Override
    void validarBloqueoDeAgregarEntidadesBase(){
        throw new BloqueoDeAgregarEntidadesBaseException();
    }
}
