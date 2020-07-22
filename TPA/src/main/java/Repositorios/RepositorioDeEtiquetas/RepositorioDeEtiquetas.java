package Repositorios.RepositorioDeEtiquetas;

import Direccion.Direccion;
import Direccion.Pais;
import Etiqueta.*;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeEtiquetas implements RepositorioEtiquetas {

    private final Etiqueta etiquetaAmoblamiento  = new EtiquetaPersonalizable( 1,"amoblamiento");
    private final Direccion direccion = new Direccion(new RepositorioDeLocacionesMeli(), "Cervantes", 607, 5, "1407", Pais.AR);
    private final Proveedor proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccion);
    private final Etiqueta etiquetaProveedorJuanPerez = new EtiquetaProveedor(2, proveedor);
    private final Etiqueta etiquetaDefecto = new EtiquetaDefecto();

    public Etiqueta getEtiquetaDefecto() {
        return etiquetaDefecto;
    }

    public List<Etiqueta> getEtiquetas() {
        List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
        etiquetas.add(etiquetaAmoblamiento);
        etiquetas.add(etiquetaProveedorJuanPerez);

        return etiquetas;

    }
    public Etiqueta getEtiquetaDadoIdentificador(int identificador) {
        return getEtiquetas()
                .stream()
                .filter( e -> e.getIdentificador() == identificador)
                .findAny()
                .orElse(null);
    }
}
