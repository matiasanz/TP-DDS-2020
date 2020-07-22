package Etiqueta;

//Null object

public class EtiquetaDefecto implements Etiqueta {

    private static EtiquetaDefecto etiquetaDefecto;

    private EtiquetaDefecto() {
    }

    public static EtiquetaDefecto getInstance() {

        if (etiquetaDefecto == null)
            etiquetaDefecto = new EtiquetaDefecto();

        return etiquetaDefecto;
    }

    @Override
    public String getNombre() {
        return "otros";
    }

    @Override
    public Integer getIdentificador() {
        return 0;
    }
}
