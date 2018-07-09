package edu.uga.discoverontology.model;

import java.util.ArrayList;
import java.util.Date;

public class SystemTestHistory {
	
	private int ID;
	private MyTestSystem mySystemTest;
	private Date date;
	private ArrayList<UnitTestHistory> unitTestHistorys;

	public SystemTestHistory() {
	}

	public SystemTestHistory(int iD, MyTestSystem mySystemTest, Date date,
			ArrayList<UnitTestHistory> unitTestHistorys) {
		super();
		ID = iD;
		this.mySystemTest = mySystemTest;
		this.date = date;
		this.unitTestHistorys = unitTestHistorys;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public MyTestSystem getMySystemTest() {
		return mySystemTest;
	}

	public void setMySystemTest(MyTestSystem mySystemTest) {
		this.mySystemTest = mySystemTest;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<UnitTestHistory> getUnitTestHistorys() {
		return unitTestHistorys;
	}

	public void setUnitTestHistorys(ArrayList<UnitTestHistory> unitTestHistorys) {
		this.unitTestHistorys = unitTestHistorys;
	}

	@Override
	public String toString() {
		return "SystemTestHistory [ID=" + ID + ", mySystemTest=" + mySystemTest + ", date=" + date
				+ ", unitTestHistorys=" + unitTestHistorys + "]";
	}

	
}
