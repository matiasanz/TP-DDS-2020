package Categoria;

public class ValidadorNoMasEntidadesbase extends Validador{
    @Override
    void validarBloqueoDeAgregarEntidadesBase(){
        throw new BloqueoDeAgregarEntidadesBaseException();
    }
}
