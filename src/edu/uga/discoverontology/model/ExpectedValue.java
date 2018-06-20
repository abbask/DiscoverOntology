package edu.uga.discoverontology.model;

public class ExpectedValue {
	
	private int ID;
	private String subject;
	private String predicate;
	private String object;
	private MyUnitTest unitTest;
	
	
	
	public ExpectedValue() {
	}

	public ExpectedValue(String subject, String predicate, String object) {
		super();
		this.subject = subject;
		this.predicate = predicate;
		this.object = object;
	}

	public ExpectedValue(int iD, String subject, String predicate, String object, MyUnitTest unitTest) {
		super();
		ID = iD;
		this.subject = subject;
		this.predicate = predicate;
		this.object = object;
		this.unitTest = unitTest;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public MyUnitTest getUnitTest() {
		return unitTest;
	}

	public void setUnitTest(MyUnitTest unitTest) {
		this.unitTest = unitTest;
	}

	@Override
	public String toString() {
		return "ExpectedValues [ID=" + ID + ", subject=" + subject + ", predicate=" + predicate + ", object=" + object
				+ ", unitTest=" + unitTest + "]";
	}
	
	
	
	
	

}
