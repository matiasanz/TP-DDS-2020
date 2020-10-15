package Repositorios;

import Entidad.Entidad;

public class RepoEntidadesDB extends RepoDB<Entidad>{

	@Override
	protected String className(){
		return "Entidad";
	}

}
