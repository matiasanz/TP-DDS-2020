package Factory;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceFactory {
	
	private static BasicDataSource createDatasource(String db) {
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

}