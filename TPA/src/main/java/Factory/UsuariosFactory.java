package Factory;

import Usuario.Usuario;

public class UsuariosFactory {
	public static Usuario usuarioStub() {
		return new Usuario("usuario", "Tp2020Dds");
	}
}
