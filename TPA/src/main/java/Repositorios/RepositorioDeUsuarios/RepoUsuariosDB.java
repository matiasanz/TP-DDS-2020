package Repositorios.RepositorioDeUsuarios;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class RepoUsuariosDB extends RepositorioDeUsuarios{

	private BasicDataSource baseDeDatos;
	
	public RepoUsuariosDB(){
		
	}
}
