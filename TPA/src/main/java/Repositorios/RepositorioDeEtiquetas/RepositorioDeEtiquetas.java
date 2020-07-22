package Repositorios.RepositorioDeEtiquetas;

import Direccion.Direccion;
import Direccion.Pais;
import Etiqueta.*;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeEtiquetas implements RepositorioEtiquetas {

    List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();

    public List<Etiqueta> getEtiquetas() {
        etiquetas.add(this.getEtiquetaDefecto());
        etiquetas.add(this.getEtiquetaAmoblamiento());
        etiquetas.add(this.getEtiquetaProveedorJuanPerez());


        return etiquetas;
    }

    @Override
    public void agregarEtiqueta(Etiqueta etiqueta) {
        validarEtiqueta(etiqueta);
        etiquetas.add(etiqueta);
    }

    private void validarEtiqueta(Etiqueta etiqueta) {

        if (etiquetas.stream().filter(e -> e.getIdentificador() == etiqueta.getIdentificador()).count() > 0) {
            throw new EtiquetaConIdentificadorDuplicadoException(etiqueta.getIdentificador());
        }

        if (etiquetas.stream().filter(e -> e.getNombre() == etiqueta.getNombre()).count() > 0) {
            throw new EtiquetaConNombreDuplicadoException(etiqueta.getNombre());
        }

    }

    public Etiqueta getEtiquetaProveedorJuanPerez() {
        Direccion direccion = new Direccion(new RepositorioDeLocacionesMeli(), "Cervantes", 607, 5, "1407", Pais.AR);
        Proveedor proveedor = Proveedor.PersonaFisica(22222222, 1222222224, "Juan", "Perez", direccion);
        return new EtiquetaProveedor(2, proveedor);
    }

    public Etiqueta getEtiquetaDefecto() {
        return new EtiquetaDefecto();
    }

    public Etiqueta getEtiquetaAmoblamiento() {
        return new EtiquetaPersonalizable(1, "amoblamiento");
    }

    public Etiqueta getEtiquetaDadoIdentificador(int identificador) {
        return getEtiquetas()
                .stream()
                .filter(e -> e.getIdentificador() == identificador)
                .findAny()
                .orElse(null);
    }
}
