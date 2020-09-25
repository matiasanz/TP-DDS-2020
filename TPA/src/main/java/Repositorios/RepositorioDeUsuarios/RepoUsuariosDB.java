package Repositorios.RepositorioDeUsuarios;
import org.apache.commons.dbutils.ResultSetHandler;

import Organizacion.IngresoFallidoException;
import Repositorios.BaseDeDatos;
import Usuario.Usuario;

public class RepoUsuariosDB extends RepositorioDeUsuarios{

	public BaseDeDatos baseDeDatos;
	
	public RepoUsuariosDB(BaseDeDatos dataBase){
		this.baseDeDatos = dataBase;
	}
	
	@Override
	public void add(Usuario usuario){

		baseDeDatos.insert("usuarios(username, contrasenia)"
				,	entreComillasSimples(usuario.getUsername())
				,   entreComillasSimples(usuario.getContrasenia())
				);
		
		usuarios.add(usuario);
	}
	
	@Override
	public Usuario getUsuario(String nombre, String contrasenia){
		Usuario usuario;
		
		try{
			usuario = super.getUsuario(nombre, contrasenia);
		
		} catch (UsuarioDesconocidoException e){
			usuario = this.getByUsernameYContrasenia(nombre,contrasenia);
			this.cargarBandejaDeUsuario(usuario);
			this.add(usuario);
		}
		
		return usuario;
	}
	
	public Usuario getByUsernameYContrasenia(String username, String contrasenia){
		
		ResultSetHandler<Usuario> handler = (rs) -> {
			Usuario usuario = null;

			if(rs.next()) {
				usuario = new Usuario(rs.getString("username"), rs.getString("contrasenia"));
			}

			return usuario;
		};
		
		String where = "username=" + entreComillasSimples(username) 
			+ " and contrasenia=" + entreComillasSimples(contrasenia);
		
		Usuario usuario = baseDeDatos.select("usuarios", where, handler);
		
		if(usuario==null){
			throw new IngresoFallidoException();
		}
		
		return usuario;
	}
	
	@Override
	public void eliminarUsuario(Usuario usuario){
		baseDeDatos.delete("usuarios", "username =" + entreComillasSimples(usuario.getUsername()));		
	}
	
	public void cargarBandejaDeUsuario(Usuario usuario){
		ResultSetHandler<Void> handler = (rs) -> {
			
			while(rs.next()) {
				usuario.notificarEvento(rs.getString("mensaje"));
			}

			return null;
		};

		String where = "usuario_id=" + Long.toString(usuario.getId());
		
		baseDeDatos.select("mensaje_usuario",where,handler);
	}
	
	private String entreComillasSimples(String s){
		return "\'" + s + "\'";
	}
}
