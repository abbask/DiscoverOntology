package edu.uga.discoverontology.model;

import java.util.ArrayList;

public class MyUnitTest {
	
	private int ID;
	private String name;
	private String assertType;
	private String query;
	private String message;
	private MyTestSystem systemTest;
	private ArrayList<ExpectedValuesGroup> expectedValueGroups;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getAssertType() {
		return assertType;
	}
	public void setAssertType(String assertType) {
		this.assertType = assertType;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public MyTestSystem getSystemTest() {
		return systemTest;
	}
	public void setSystemTest(MyTestSystem systemTest) {
		this.systemTest = systemTest;
	}
	
	
	public ArrayList<ExpectedValuesGroup> getExpectedValueGroups() {
		return expectedValueGroups;
	}
	public void setExpectedValueGroups(ArrayList<ExpectedValuesGroup> expectedValueGroups) {
		this.expectedValueGroups = expectedValueGroups;
	}
	@Override
	public String toString() {
		return "MyUnitTest [ID=" + ID + ", name=" + name + ", assertType=" + assertType + ", query=" + query
				+ ", message=" + message + ", systemTest=" + systemTest + ", expectedValues=" + expectedValueGroups + "]";
	}
	
	

	


}
