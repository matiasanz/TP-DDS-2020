package Repositorios;

import MedioDePago.MedioDePago;

public class RepositorioMedioDePagoDB extends  RepoDB<MedioDePago>{
    @Override
    protected String className() {
        return "MedioDePago";
    }

    public MedioDePago findById(Long id) {
        return entityManager().find(MedioDePago.class, id);
    }
}
