package Repositorios;

import Entidad.Entidad;

public class RepoEntidadesDB extends RepoDB<Entidad> {

    @Override
    protected String className() {
        return "Entidad";
    }

    public Entidad findById(Long id) {
        return entityManager().find(Entidad.class, id);
    }
}
