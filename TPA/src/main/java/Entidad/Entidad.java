package Entidad;

import Categoria.Categoria;
import Compra.Compra;
import Repositorios.RepositorioDeCompras;

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
    private  RepositorioDeCompras compras;

    @Column(name = "nombre_ficticio")
    private String nombreFicticio;

    @ManyToMany
    @JoinTable(name = "entidades_por_categorias")
    private  List<Categoria> categorias;

    //Constructor
    public Entidad() {
        
    }
    public Entidad(String nombreFicticio){
        this.compras = new RepositorioDeCompras(new ArrayList<>());
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
    
    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
    }

// Compras ****************
    public List<Compra> getCompras() {
    	return compras.getCompras();
    }

    public void agregarCompra(Compra compra) {
        getCategorias().forEach(categoria -> categoria.notificarCompraAgregada(compra.getValorTotal(), this.getValorTodasLasCompras()));
        compras.agregarCompra(compra);
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
	public RepositorioDeCompras getComprasDelMes(LocalDate fecha){
		return compras.repositorioDelMes(fecha);
	}
}
