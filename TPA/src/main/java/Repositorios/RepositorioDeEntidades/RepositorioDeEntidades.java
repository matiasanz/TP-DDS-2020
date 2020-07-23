package Repositorios.RepositorioDeEntidades;

import Entidad.*;
import Repositorios.RepositorioDeCompras.RepositorioDeCompras;
import Fabrica.Fabrica;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedas;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeEntidades {
    private List<Entidad> entidades = new ArrayList<>();
    private RepositorioDeMonedas repositorioDeMonedas;

    public RepositorioDeEntidades(RepositorioDeMonedas repositorioDeMonedas) {
        this.repositorioDeMonedas = repositorioDeMonedas;
    }

    public List<Entidad> getEntidades() {
        return entidades;
    }

    public void nuevaEntidad(Entidad entidad) {
        entidades.add(entidad);
    }

    public Entidad getEntidadConCompras() {
        Entidad entidad = new OrganizacionSectorSocial("Entidad de Prueba", "Entidad Real", "1222222224", Fabrica.direccionStub(), 845, new ArrayList<>());
        RepositorioDeCompras repoCompras = new RepositorioDeCompras(repositorioDeMonedas);

        repoCompras.getComprasConPresupuestoElegido().forEach(compra -> entidad.agregarCompra(compra));
        return entidad;
    }
}
