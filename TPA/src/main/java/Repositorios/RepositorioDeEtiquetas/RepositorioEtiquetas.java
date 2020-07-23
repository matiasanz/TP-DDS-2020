package Repositorios.RepositorioDeEtiquetas;

import Direccion.Direccion;
import Direccion.Pais;
import Etiqueta.*;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEtiquetas {

    List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();

    public List<Etiqueta> getEtiquetas() {
        etiquetas.add(this.getEtiquetaDefecto());
        etiquetas.add(this.getEtiquetaAmoblamiento());
        etiquetas.add(this.getEtiquetaProveedorJuanPerez());


        return etiquetas;
    }

    public void agregarEtiqueta(Etiqueta etiqueta) {
        validarEtiqueta(etiqueta);
        etiquetas.add(etiqueta);
    }

    private void validarEtiqueta(Etiqueta etiqueta) {

        if (etiquetas.stream().filter(e -> e.getNombre().equals(etiqueta.getNombre())).count() > 0) {
            throw new EtiquetaConNombreDuplicadoException(etiqueta.getNombre());
        }

    }

    public Etiqueta getEtiquetaProveedorJuanPerez() {
        Direccion direccion = new Direccion(new RepositorioDeLocacionesMeli(), "Cervantes", 607, 5, "1407", Pais.AR);
        Proveedor proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccion);
        return new EtiquetaProveedor(proveedor);
    }

    public Etiqueta getEtiquetaDefecto() {
        return new EtiquetaDefecto();
    }

    public Etiqueta getEtiquetaAmoblamiento() {
        return new EtiquetaPersonalizable("amoblamiento");
    }

}
