package Factory;

import Usuario.TipoUsuario;
import Usuario.Usuario;

public class UsuariosFactory {
	public static Usuario usuarioStub() {
		return new Usuario("usuario", "Tp2020Dds", TipoUsuario.ADMINISTRADOR);
	}

	public static Usuario sinValidaciones(String username, String password)	{
		Usuario usuarioNoValidado = new Usuario();
		usuarioNoValidado.setUsername(username);
		usuarioNoValidado.setContrasenia(password);
		usuarioNoValidado.setTipo(TipoUsuario.ADMINISTRADOR);
		
		return usuarioNoValidado;
	}
}
