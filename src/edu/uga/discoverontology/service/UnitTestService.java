package edu.uga.discoverontology.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import edu.uga.discoverontology.datastore.MySQLConnection;
import edu.uga.discoverontology.model.MyUnitTest;
import edu.uga.discoverontology.presentation.SystemTestNew;

public class UnitTestService {
	
	final static Logger logger = Logger.getLogger(UnitTestService.class);
	
	public ArrayList<MyUnitTest> listAll() {
		
		ArrayList<MyUnitTest> list = new ArrayList<>();
		MySQLConnection conn = new MySQLConnection();
		try {

		PreparedStatement prepStatement = conn.openConnection().prepareStatement("SELECT * FROM suite_tests");
		ResultSet resObj = prepStatement.executeQuery();
		while(resObj.next()) {
			MyUnitTest myUnitTest = new MyUnitTest();
			myUnitTest.setName(resObj.getString("name"));
			list.add(myUnitTest);
        }
		
		} catch (Exception sqlException) {
			logger.error(sqlException.getMessage(), sqlException);
		}
		logger.info("UnitTestService.listAll :  unit_tests retrieved.");
		return list;
	}

}
