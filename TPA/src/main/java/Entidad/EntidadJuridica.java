package Entidad;
import Direccion.Direccion;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "entidades_juridica")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class EntidadJuridica extends Entidad {

	@Column(name = "razon_social")
	private String razonSocial;

	private String cuit;

	@Embedded
	private Direccion direccionPostal;

	@Column(name = "codigo_igj")
	private int codigoIGJ;

	@OneToMany
	@JoinTable(name = "entidades_por_entidad_juridica", inverseJoinColumns=@JoinColumn(name="entidad_base_id"))
	private List<EntidadBase> entidadesBase;
	
	
	public EntidadJuridica() {
		
	}
	
	public EntidadJuridica(String razonSocial, String nombreFicticio, String cuit, Direccion direccionPostal, int codigoIGJ,
			List<EntidadBase> entidadesBase) {
		super(nombreFicticio);
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codigoIGJ = codigoIGJ;
		this.entidadesBase = entidadesBase;
	}

	public void agregarEntidadBase(EntidadBase entidad){
		getCategorias().forEach(categoria -> categoria.notificarEntidadBaseAgregada());
		entidad.getCategorias().forEach(categoria -> categoria.notificarMeAgregueAUnaJuridica(this));
		entidadesBase.add(entidad);
	}

	public int getCodigoIGJ() {
		return codigoIGJ;
	}

	public void setCodigoIGJ(int codigoIGJ) {
		this.codigoIGJ = codigoIGJ;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public List<EntidadBase> getEntidadesBase() {
		return entidadesBase;
	}

	public void setEntidadesBase(List<EntidadBase> entidadesBase) {
		this.entidadesBase = entidadesBase;
	}

	public Direccion getDireccionPostal() {
		return direccionPostal;
	}

	public void setDireccionPostal(Direccion direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

}