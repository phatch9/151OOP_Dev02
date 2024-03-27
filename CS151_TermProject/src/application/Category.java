package application;

public class Category implements BeanInt{
	private String[] colName = {"name"};
	private String[] colType = {"STRING"};
	private String[] restrictionCol = {"name"};
	private String tableName = "category";
	private String catName;
	
	public Category(String name) {
		catName = name;
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
	
	public String getCatName() {
		return catName;
	}
	
	public String[] getEntryVal() {
		String[] entryVal = {catName};
		return entryVal;
	}
	
	
}
