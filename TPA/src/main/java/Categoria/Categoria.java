package Categoria;

import Entidad.EntidadJuridica;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue
    private long id;

    String nombre;
    
    @OneToMany
    private List<Validador> validadores;

    public Categoria() {
    	
    }
    
    public Categoria(String nombre) {
        this.nombre = nombre;
        this.validadores = new ArrayList<Validador>();
    }

    void agregarValidador(Validador validador){
        validadores.add(validador);
    }

    void eliminarValidador(Validador validador) {
        validadores.remove(validador);
    }

    public void notificarCompraAgregada(BigDecimal montoCompra, BigDecimal montoAcumuladoEntidad){
        validadores.stream().forEach(validador -> validador.validarGasto(montoCompra, montoAcumuladoEntidad));
    }

    public void notificarEntidadBaseAgregada(){
        validadores.stream().forEach(validador -> validador.validarBloqueoDeAgregarEntidadesBase());
    }

    public void notificarMeAgregueAUnaJuridica(EntidadJuridica entidad){
        validadores.stream().forEach(validador -> validador.validarSiEntidadJuridicaEstaBloqueada(entidad));
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Validador> getValidadores() {
        return validadores;
    }

    public void setValidadores(List<Validador> validadores) {
        this.validadores = validadores;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
