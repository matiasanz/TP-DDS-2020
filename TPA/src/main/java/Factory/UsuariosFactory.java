package Factory;

import Usuario.TipoUsuario;
import Usuario.Usuario;
import Usuario.Mensaje;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuariosFactory {
	public static Usuario usuarioStub() {
		return new Usuario("usuario", "Tp2020Dds", TipoUsuario.ADMINISTRADOR);
	}

	public static Usuario sinValidaciones(String username, String password)	{
		
		List<Mensaje> bandejaDeMensajes = new ArrayList<>();
		bandejaDeMensajes.add(new Mensaje(LocalDateTime.now(), "Este es un mensaje"));
		bandejaDeMensajes.add(new Mensaje(LocalDateTime.now().minusMonths(2), "Este es otro mensaje"));
		Usuario usuarioNoValidado = new Usuario();
		usuarioNoValidado.setUsername(username);
		usuarioNoValidado.setContrasenia(password);
		usuarioNoValidado.setTipo(TipoUsuario.ADMINISTRADOR);
		usuarioNoValidado.setBandejaDeMensajes(bandejaDeMensajes);
		return usuarioNoValidado;
	}
}
