package Entidad;

import Categoria.Categoria;
import Compra.Compra;
import Repositorios.RepositorioDeCompras.RepositorioDeComprasMemoria;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "entidades")
public abstract class Entidad {
    @Id
    @GeneratedValue
    private Long id;

    @Transient
    private  RepositorioDeComprasMemoria compras;

    @Column(name = "nombre_ficticio")
    private String nombreFicticio;

    @ManyToMany
    @JoinTable(name = "entidades_por_categorias")
    private  List<Categoria> categorias;

    //Constructor
    public Entidad() {
        
    }
    public Entidad(String nombreFicticio){
        this.compras = new RepositorioDeComprasMemoria(new ArrayList<>());
        this.categorias = new ArrayList<>();
        this.nombreFicticio = nombreFicticio;
    }

	public Long getId()
	{
		return id;
	}
// Categoria ****************

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
    }

    public void eliminarCategoria(Categoria categoria) { categorias.remove(categoria); }
// Compras ****************
    public List<Compra> getCompras() {
    	return compras.getCompras();
    }

    public void agregarCompra(Compra compra) {
        getCategorias().forEach(categoria -> categoria.notificarCompraAgregada(compra.getValorTotal(), this.getValorTodasLasCompras()));
        compras.agregar(compra);
    }

    public BigDecimal getValorTodasLasCompras() {
        return compras.getCompras().stream().map(Compra::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Compra> getComprasPendientesDeValidacion() {
    	return compras.getComprasPendientesDeAprobacion();
    }        

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreFicticio() {
		return nombreFicticio;
	}

	public void setNombreFicticio(String nombreFicticio) {
		this.nombreFicticio = nombreFicticio;
	}
	public RepositorioDeComprasMemoria getComprasDelMes(LocalDate fecha){
		return compras.repositorioDelMes(fecha);
	}
}
