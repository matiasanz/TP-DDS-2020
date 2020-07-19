package Etiqueta;

public class EtiquetaPersonalizable implements Etiqueta{
    private String nombre;

    @Override
    public boolean cumpleCriterio(String stringComparador) {
        return this.nombre.equalsIgnoreCase(stringComparador);
    }
}
