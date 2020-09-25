package Repositorios;

import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class BaseDeDatos
{
	private BasicDataSource baseDeDatos;

	public BaseDeDatos(){
		this.baseDeDatos = createDatasource();
	}

	public <T> T select(String from, String where, ResultSetHandler<T> handler){
		
		String sql = "select * "
				+ "from " + from + " "
				+ "where " + where;

		return doQuery(sql, handler);
	}
	
	public void insert(String into, String ... values){

		ResultSetHandler<Void> handler = (rs) -> {return null;};

		String sql = "insert into " + into + " values(";
		
		for(String value: values){
			sql += value + ","; 
		}
		
		sql = replaceLast(sql, ")");

		try{
			QueryRunner run = new QueryRunner(this.baseDeDatos);
			run.insert(sql, handler);
		}
		catch (SQLException e){
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(String from, String where){
		String sql = "delete from " + from + " where " + where ;

		try{
			QueryRunner run = new QueryRunner(this.baseDeDatos);
			run.update(sql);
		}
		catch (SQLException e){
			throw new RuntimeException(e.getMessage());
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
	
	private BasicDataSource createDatasource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:hsqldb:mem:app-db");
		dataSource.setMaxTotal(10);
		dataSource.setMaxIdle(5);
		dataSource.setInitialSize(5);
		return dataSource;
	}
	
	private String replaceLast(String string, String sustituto){
		return string.substring(0, string.length() - 1) + sustituto;
	}

}
