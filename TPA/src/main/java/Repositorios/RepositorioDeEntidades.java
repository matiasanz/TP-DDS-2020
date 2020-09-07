package Repositorios;

import Entidad.*;
import Repositorios.RepositorioDeCompras;
import Fabrica.Fabrica;
import Factory.ComprasFactory;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeEntidades {
    private List<Entidad> entidades = new ArrayList<>();

  //SE VAAAAAAAAAAAAA
    private RepositorioDeMonedas repositorioDeMonedas;

    public RepositorioDeEntidades(RepositorioDeMonedas repositorioDeMonedas) {
        this.repositorioDeMonedas = repositorioDeMonedas;
    }
  //******************

    public List<Entidad> getEntidades() {
        return entidades;
    }

    public void agregarEntidad(Entidad entidad) {
        entidades.add(entidad);
    }

    
    //HARDCODEADO
    public Entidad getEntidadConCompras() {
        Entidad entidad = new OrganizacionSectorSocial("Entidad de Prueba", "Entidad Real", "1222222224", Fabrica.direccionStub(), 845, new ArrayList<>());
        ComprasFactory.getComprasConPresupuestoElegido().forEach(compra -> entidad.agregarCompra(compra));
        return entidad;
    }
}
