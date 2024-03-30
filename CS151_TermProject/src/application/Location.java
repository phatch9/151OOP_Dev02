package application;

public class Location implements BeanInt{
	private String[] colName = {"name", "description"};
	private String[] colType = {"STRING", "STRING"};
	private String[] restrictionCol = {"name"};
	private String tableName = "location";
	private String locationName;
	private String description;
	
	public Location(String name, String desc) {
		locationName = name;
		description = desc;
	}
	
	public String[] getColName() {
		return colName;
	}
	
	public String[] getColType() {
		return colType;
	}
	
	public String[] getRestrictionCol() {
		return restrictionCol;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String[] getEntryVal() {
		String[] entryVal = {locationName, description};
		return entryVal;
	}
	
	
}
