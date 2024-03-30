package application;

public interface BeanInt {
	// Returns a String[] of column names
	public String[] getColName();
	
	// Returns a String[] of the data types of each column
	public String[] getColType();
	
	// Returns a String[] of the name of the columns that want to enforce uniqueness
	public String[] getRestrictionCol();
	
	// Returns a String that represents the name of the table in the DB
	public String getTableName();
	
	// Returns a String[] that represent a row of values to be entered into the table
	public String[] getEntryVal();
}
