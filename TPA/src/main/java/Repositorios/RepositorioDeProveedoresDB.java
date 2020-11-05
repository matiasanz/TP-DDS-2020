package Repositorios;

import Proveedor.Proveedor;

public class RepositorioDeProveedoresDB extends RepoDB<Proveedor>{
    @Override
    protected String className(){
        return "Proveedor";
    }

    public Proveedor findById(Long id) {
        return entityManager().find(Proveedor.class, id);
    }
}
