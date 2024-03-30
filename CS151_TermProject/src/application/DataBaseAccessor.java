package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseAccessor {
	private static DataBaseAccessor singleInstance = new DataBaseAccessor();
	
	private DataBaseAccessor() {}
	
	// Returns singleton instance
	public static DataBaseAccessor getSingleInstance() {
		return singleInstance;
	}
	
	public boolean addEntry(BeanInt beanObj) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		
		// Grab data from the Java bean
		String tableName = beanObj.getTableName();
		String[] colName = beanObj.getColName();
		String[] colType = beanObj.getColType();
		String[] entryVal = beanObj.getEntryVal();
		String[] restrictionCol = beanObj.getRestrictionCol();
		String columns = "";
		String restrictions = ", ";
		String values = "";
		
		// Builds string values for column initialization query
		for (int i = 0; i < colName.length; i++) {
			columns += colName[i] + " " + colType[i];
			values += "'" + entryVal[i] + "'";
			
			if (i != colName.length - 1) {
				columns += ", ";
				values += ", ";
			}
		}
		
		// Builds string for unique restriction query
		for (int i = 0; i < restrictionCol.length; i++) {
			restrictions += "UNIQUE(" + restrictionCol[i] + ")";
			if (i != restrictionCol.length - 1) {
				restrictions += ", ";
			}
		}
		
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:assetRacoon.db");
	
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			
			// Creates new table based on params if one does not exist
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + "(" + columns + restrictions + ")");
			
			// Inserts values into database and returns false if value already exists in database
			if (statement.executeUpdate("INSERT INTO " + tableName + " values(" + values + ") ON CONFLICT DO NOTHING RETURNING *") == 0) {
				return false;
			}
		}
		catch(SQLException e){  System.err.println(e.getMessage()); }       
		finally {         
			try {
				if(connection != null)
					connection.close();
               	}
			catch(SQLException e) {         
				System.err.println(e); 
			}
		}
		
		return true;
	}
}
