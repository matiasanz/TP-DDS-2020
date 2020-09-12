package Factory;

import Organizacion.Organizacion;
import Repositorios.RepositorioDeCategorias;
import Repositorios.RepositorioDeEntidades;
import Repositorios.RepositorioDeUsuarios.RepositorioDeUsuarios;

public class OrganizacionesFactory {
	public static Organizacion organizacionStub() {
		return new Organizacion(new RepositorioDeUsuarios(), new RepositorioDeCategorias(), new RepositorioDeEntidades());
	}
}
