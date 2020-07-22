package Etiqueta;

//Null object

public class EtiquetaDefecto implements Etiqueta {

    @Override
    public String getNombre() {
        return "otros";
    }

    @Override
    public Integer getIdentificador() {
        return 0;
    }
}
