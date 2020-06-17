package resourceManagers;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class AzureDBUtils {

	static ConfigFileReader configFileReader = new ConfigFileReader();

	//GET DB Configurations
	private String hostName=configFileReader.getGG_DBHostName();
	private String dbName=configFileReader.getGG_DBName() ;
	private String user=configFileReader.getGG_DBUserName() ;
	private String password=configFileReader.getGG_DBPassword() ;

	//Connect to Azure DB
	public Connection connectToDB() {
		String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
				+ "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(url);
			String schema = connection.getSchema();
			System.out.println("Successful connection - Schema: " + schema);
		}
		catch(Exception ex) {
			throw new RuntimeException(ex.getMessage());

		}
		return connection;
	}

	public ResultSet getRecordFromDB(String sqlQuery) {
		ResultSet resultSet;
		try {
			Connection connection=connectToDB();
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);
		}

		catch (Exception e) {
			resultSet=null;
		}
		return resultSet;
	}
	
	public List<Map<String,String>> getResultsInHashMap(ResultSet resultset) {
		List<Map<String,String>> DBDataMap= new ArrayList<Map<String,String>>();
		try {
			if (resultset!=null) {
				int totalCol=resultset.getMetaData().getColumnCount();
				
				while (resultset.next()) {
					String ColName="";
					String ColValue="";
					Map<String,String> DataMap=new HashMap();
					for (int icol=1;icol<=totalCol;icol++) {
						ColName=resultset.getMetaData().getColumnName(icol);
//						System.out.println(ColName);
						ColValue=resultset.getString(icol);
						DataMap.put(ColName, ColValue);
					}
					DBDataMap.add(DataMap);
				}
			}
		}
		catch(Exception ex) {
			DBDataMap=null;
		}
		return DBDataMap;
	}
}
