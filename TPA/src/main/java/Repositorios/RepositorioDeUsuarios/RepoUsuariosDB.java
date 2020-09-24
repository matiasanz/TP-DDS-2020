package Repositorios.RepositorioDeUsuarios;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import Usuario.Usuario;

public class RepoUsuariosDB extends RepositorioDeUsuarios{

	private BasicDataSource baseDeDatos;
	
	public RepoUsuariosDB(BasicDataSource dataSource){
		super();
		this.baseDeDatos = dataSource;
	}
	
	@Override
	public Usuario getUsuario(String nombre, String contrasenia){
		Usuario usuario;
		
		try{
			usuario = super.getUsuario(nombre, contrasenia);
		} catch (UsuarioDesconocidoException e){
			usuario = this.getUsuarioFromDB(nombre,contrasenia);
			
			if(usuario==null){
				throw e;
			}
			
			this.cargarBandejaDeUsuario(usuario);
			this.agregarUsuario(usuario);
		}
		
		return usuario;
	}
	
	public Usuario getUsuarioFromDB(String nombre, String contrasenia){
		
		ResultSetHandler<Usuario> handler = (rs) -> {
			Usuario usuario = null;
			
			if(rs.next()) {
				usuario = new Usuario(rs.getString("username"), rs.getString("contrasenia"));
				this.agregarUsuario(usuario);
			}
			
			return usuario;
		};
		
		String sql =
				"select * "
				+ "from usuarios u"
				+ "where username=" + nombre + "and" + "contrasenia=" + contrasenia;
		
		Usuario usuario = doQuery(sql , handler);
		
		return usuario;
	}
	
	public void cargarBandejaDeUsuario(Usuario usuario){
		ResultSetHandler<Void> handler = (rs) -> {
			
			while(rs.next()) {
				usuario.notificarEvento(rs.getString("mensaje"));
			}

			return null;
		};
		
		String sql = "select * "
				+ 		"from mensaje_usuario"
				+ 		"where usuario_id=" + Long.toString(usuario.getId());
		
		doQuery(sql, handler);
	}
	
	public void actualizarBaseDeDatos(){
		//TODO Programar aqui...
	}
	
	private <T> T doQuery(String sql, ResultSetHandler<T> handler) {
		try {
			QueryRunner run = new QueryRunner(this.baseDeDatos);
				return run.query(sql, handler);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
