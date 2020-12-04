package Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue
    private Long id;


    private int identificador;
    @Transient
    private LocalDateTime fecha;

    @Column(name = "fecha")
    private String fechaamigable;

    private String value;

    public Mensaje(LocalDateTime fecha, String value, int identificador) {
        this.fecha = fecha;
        this.value = value;
        this.identificador = identificador;
        this.setFechaamigable(fecha);
    }

    public Mensaje() {
    }


// de aca para abajo getters y setters

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFechaamigable() {
        return this.fechaamigable;
    }

    public String getTiempoAmigable(int horario) {
        return horario < 10 ? "0" + horario : String.valueOf(horario);
    }

    public void setFechaamigable(LocalDateTime fechaCompleta) {
        this.fechaamigable = fechaCompleta.getDayOfMonth() + "/" + fechaCompleta.getMonthValue() + "/"
                + fechaCompleta.getYear() + "                      " + "Hora " + getTiempoAmigable(fechaCompleta.getHour()) + ":"
                + getTiempoAmigable(fechaCompleta.getMinute());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador() {
        this.identificador = identificador;
    }
}