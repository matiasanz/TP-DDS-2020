package Organizacion;

import Factory.EntidadesFactory;
import Factory.OrganizacionesFactory;
import Usuario.Usuario;

public class OrganizacionMock{
	public static Organizacion self = null;

	private static void crearOrganizacionMock(){
		self = OrganizacionesFactory.organizacionStub();
		Usuario usuario = self.getUsuarioEspecifico("usuario");
		self.agregarEntidad(EntidadesFactory.entidadConComprasParaUsuario(usuario));
	}
	public static Organizacion getInstance(){
		if(self==null){
			crearOrganizacionMock();
		}
		return self;
	}
}