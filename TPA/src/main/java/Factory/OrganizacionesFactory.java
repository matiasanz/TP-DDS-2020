package Factory;

import Organizacion.Organizacion;
import Repositorios.RepositorioDeCategorias;
import Repositorios.RepositorioDeEntidades;
import Repositorios.RepositorioDeUsuarios.RepositorioDeUsuarios;

public class OrganizacionesFactory {
	public static Organizacion organizacionStub() {
		RepositorioDeUsuarios usuarios = new RepositorioDeUsuarios();
		usuarios.add(UsuariosFactory.usuarioStub());
		return new Organizacion(usuarios, new RepositorioDeCategorias(), new RepositorioDeEntidades());
	}
}
