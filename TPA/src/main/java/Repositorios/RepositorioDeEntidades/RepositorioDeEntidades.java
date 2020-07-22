package Repositorios.RepositorioDeEntidades;
import Direccion.Direccion;
import Direccion.Pais;
import Entidad.*;
import Repositorios.RepositorioDeCompras.RepositorioDeCompras;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;

import java.util.ArrayList;

public class RepositorioDeEntidades {

    private final Direccion direccion = new Direccion(new RepositorioDeLocacionesMeli(), "Cervantes", 607, 5, "1407", Pais.AR);
    private final RepositorioDeCompras repoCompras = new RepositorioDeCompras();

    public Entidad getEntidadConCompras() {

        Entidad entidad = new OrganizacionSectorSocial("Entidad de Prueba", "Entidad Real", "1222222224", direccion, 845, new ArrayList<>());

        repoCompras.getComprasConPresupuestoElegido().forEach(compra -> entidad.agregarCompra(compra));
        return entidad;
    }
}
