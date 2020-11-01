package Factory;

import Usuario.TipoUsuario;
import java.util.ArrayList;
import java.util.List;
import Usuario.Usuario;

public class UsuariosFactory {
	public static Usuario usuarioStub() {
		return new Usuario("usuario", "Tp2020Dds", TipoUsuario.ADMINISTRADOR);
	}

	public static Usuario sinValidaciones(String username, String password)	{
		
		List<String> bandejaDeMensajes=new ArrayList<>();
		bandejaDeMensajes.add("este es un mensaje");
		bandejaDeMensajes.add("este es otro mensaje");
		Usuario usuarioNoValidado = new Usuario();
		usuarioNoValidado.setUsername(username);
		usuarioNoValidado.setContrasenia(password);
		usuarioNoValidado.setTipo(TipoUsuario.ADMINISTRADOR);
		usuarioNoValidado.setBandejaDeMensajes(bandejaDeMensajes);
		return usuarioNoValidado;
	}
}
