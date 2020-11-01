package Usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;

    private String value;

    public Mensaje(LocalDate fecha, String value) {
        this.fecha = fecha;
        this.value = value;
    }

    public Mensaje() {

    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}