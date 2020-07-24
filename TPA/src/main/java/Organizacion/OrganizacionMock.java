package Organizacion;

import Fabrica.Fabrica;
import Usuario.Usuario;

public class OrganizacionMock{
	
	public static Organizacion self = null;

	private static void crearOrganizacionMock(){
		self = Fabrica.organizacionStub();
		Usuario usuario = self.getUsuarioEspecifico("usuario","Tp2020Dds");
		self.agregarEntidad(Fabrica.entidadConComprasParaUsuario(usuario));
	}
	
	public static Organizacion getInstance(){
		if(self==null){
			crearOrganizacionMock();
		}

		return self;
	}
}