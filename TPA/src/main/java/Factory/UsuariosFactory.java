package Factory;

import Usuario.TipoUsuario;
import Usuario.Usuario;
import Usuario.Mensaje;

import java.time.LocalDateTime;

public class UsuariosFactory {
	public static Usuario usuarioStub() {
		return new Usuario("usuario", "Tp2020Dds", TipoUsuario.ADMINISTRADOR);
	}

	public static Usuario usuarioConMensajes(){
		Usuario beto = new Usuario("beto", "123", TipoUsuario.ESTANDAR);
		
		beto.notificarEvento(new Mensaje(LocalDateTime.now().minusYears(7).plusMinutes(12), "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia.", 4));
		beto.notificarEvento((new Mensaje(LocalDateTime.now().minusMonths(5).minusMinutes(10), "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.", 2)));
		beto.notificarEvento(new Mensaje(LocalDateTime.now().minusMonths(2).minusHours(2).plusMinutes(2), "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings.", 302));
		beto.notificarEvento(new Mensaje(LocalDateTime.now().minusDays(2), "Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis.", 5));
		beto.notificarEvento(new Mensaje(LocalDateTime.now().minusHours(6), "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 1));
		
		return beto;
	}
}
