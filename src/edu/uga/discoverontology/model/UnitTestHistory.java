package edu.uga.discoverontology.model;

import java.util.ArrayList;

public class UnitTestHistory {
	
	private int ID;
	private SystemTestHistory systemTestHistory;
	private MyUnitTest myUnitTest;
	private String status;
	private String message;
	
	public UnitTestHistory() {
	}

	public UnitTestHistory(int iD, SystemTestHistory systemTestHistory, MyUnitTest myUnitTest, String status,
			String message) {
		super();
		ID = iD;
		this.systemTestHistory = systemTestHistory;
		this.myUnitTest = myUnitTest;
		this.status = status;
		this.message = message;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public SystemTestHistory getSystemTestHistory() {
		return systemTestHistory;
	}

	public void setSystemTestHistory(SystemTestHistory systemTestHistory) {
		this.systemTestHistory = systemTestHistory;
	}

	public MyUnitTest getMyUnitTest() {
		return myUnitTest;
	}

	public void setMyUnitTest(MyUnitTest myUnitTest) {
		this.myUnitTest = myUnitTest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UnitTestHistory [ID=" + ID + ", systemTestHistory=" + systemTestHistory + ", myUnitTest=" + myUnitTest
				+ ", status=" + status + ", message=" + message + "]";
	}

	
}
