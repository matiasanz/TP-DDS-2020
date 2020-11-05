package Usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime fecha;

    private String value;

    public Mensaje(LocalDateTime fecha, String value) {
        this.fecha = fecha;
        this.value = value;
    }

    public Mensaje() {}

    
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
    
    public Long getId(){
    	return id;
    }
    
    public void setId(Long id){
    	this.id=id;
    }
}