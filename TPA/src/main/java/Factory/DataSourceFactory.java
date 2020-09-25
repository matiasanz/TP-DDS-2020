package Factory;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceFactory {

	public static BasicDataSource createDatasource(String db) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver");
		dataSource.setUsername("root");
		dataSource.setPassword("mysql");
		dataSource.setUrl("jdbc:mysql://localhost/" + db);
		dataSource.setMaxTotal(10);
		dataSource.setMaxIdle(5);
		dataSource.setInitialSize(5);
		dataSource.setValidationQuery("SELECT 1");
		return dataSource;
	}

}