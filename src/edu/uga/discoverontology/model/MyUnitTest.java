package edu.uga.discoverontology.model;

public class MyUnitTest {
	
	private String name;
	private String assertType;
	private String query;
	private String expectedValue;
	private String message;
	private MyTestSystem systemTest;

	
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
	public String getExpectedValue() {
		return expectedValue;
	}
	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
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
	@Override
	public String toString() {
		return "MyUnitTest [name=" + name + ", assertType=" + assertType + ", expectedValue=" + expectedValue
				+ ", message=" + message + "]";
	}

	


}
