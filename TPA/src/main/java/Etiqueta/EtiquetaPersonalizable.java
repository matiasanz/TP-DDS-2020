package Etiqueta;

public class EtiquetaPersonalizable implements Etiqueta {

    private String nombre;

    public EtiquetaPersonalizable(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

}
