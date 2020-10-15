package Organizacion;

import Entidad.Entidad;
import Factory.EntidadesFactory;
import Factory.OrganizacionesFactory;
import Usuario.Usuario;

public class OrganizacionMock{
	public static Organizacion self = null;

	private static void crearOrganizacionMock(){
		self = OrganizacionesFactory.organizacionStub();		
		Entidad entidad = EntidadesFactory.getEntidadConCompras();
		
		Usuario usuario = self.getUsuarioEspecifico("usuario");
		self.agregarEntidad(EntidadesFactory.entidadConComprasParaUsuario(usuario));
		self.agregarEntidad(entidad);	
	}
	public static Organizacion getInstance(){
		if(self==null){
			crearOrganizacionMock();
		}
		return self;
	}
}