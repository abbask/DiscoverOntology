package model;

public class MyUnitTest {
	
	private String name;
	private AssertType assertType;
	private String query;
	private String expectedValue;
	private String message;

	
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
	public AssertType getAssertType() {
		return assertType;
	}
	public void setAssertType(AssertType assertType) {
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
	
	@Override
	public String toString() {
		return "MyUnitTest [name=" + name + ", assertType=" + assertType + ", expectedValue=" + expectedValue
				+ ", message=" + message + "]";
	}

	


}
