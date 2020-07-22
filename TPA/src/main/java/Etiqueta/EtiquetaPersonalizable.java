package Etiqueta;

public class EtiquetaPersonalizable implements Etiqueta {

    private Integer identificador;
    private String nombre;

    public EtiquetaPersonalizable(Integer identificador, String nombre) {
        this.identificador = identificador;
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public Integer getIdentificador() {
        return this.identificador;
    }
}
