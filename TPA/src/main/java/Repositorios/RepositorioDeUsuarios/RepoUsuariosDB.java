package Repositorios.RepositorioDeUsuarios;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import Organizacion.IngresoFallidoException;
import Usuario.Usuario;

import java.sql.SQLException;

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
			usuario = this.findByUsernameYContrasenia(nombre,contrasenia);

			//this.cargarBandejaDeUsuario(usuario);
			this.add(usuario);
		}
		
		return usuario;
	}
	
	public Usuario findByUsernameYContrasenia(String username, String contrasenia){
		
		ResultSetHandler<Usuario> handler = (rs) -> {
			Usuario usuario = null;

			if(rs.next()) {
				usuario = new Usuario(rs.getString("username"), rs.getString("contrasenia"));
			}

			return usuario;
		};
		
		String sql =
				"select * "
				+ "from usuarios "
				+ "where username= \'" + username + "\' and contrasenia=\'" + contrasenia + "\'";
		
		Usuario usuario = doQuery(sql , handler);
		
		if(usuario==null){
			throw new IngresoFallidoException();
		}
		
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
				+ 		"from mensaje_usuario "
				+ 		"where usuario_id=" + Long.toString(usuario.getId());
		
		doQuery(sql, handler);
	}

	@Override
	public void add(Usuario usuario){

		ResultSetHandler<Long> handler = (rs) -> {
			Long id = null;

			if(rs.next()) {
				id = new Long(rs.getString("id"));
			}

			return id;
		};

		String sql =
				"insert into usuarios(username, contrasenia)"
						+ "values("
						+ "\'" + usuario.getUsername() + "\',\'" + usuario.getContrasenia() + "\')";

		try{
			QueryRunner run = new QueryRunner(this.baseDeDatos);
			run.insert(sql, handler);
			super.add(usuario);
		}
		catch (SQLException e){
			throw new RuntimeException();
		}
	}
	
	private <T> T doQuery(String sql, ResultSetHandler<T> handler) {
		try {
			QueryRunner run = new QueryRunner(this.baseDeDatos);
				return run.query(sql, handler);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Usuario usuario){

		String sql =
				"delete from usuarios "
						+ "where username = \'" + usuario.getUsername() + "\'";

		try{
			QueryRunner run = new QueryRunner(this.baseDeDatos);
			run.update(sql);
		}
		catch (SQLException e){
			throw new RuntimeException();
		}
	}
}
