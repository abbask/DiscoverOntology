package edu.uga.discoverontology.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import edu.uga.discoverontology.datastore.MySQLConnection;
import edu.uga.discoverontology.model.MyTest;
import edu.uga.discoverontology.presentation.SystemTestNew;

public class UnitTestService {
	
	final static Logger logger = Logger.getLogger(UnitTestService.class);
	
	public ArrayList<MyTest> listAll() {
		
		ArrayList<MyTest> list = new ArrayList<>();
		MySQLConnection conn = new MySQLConnection();
		try {

		PreparedStatement prepStatement = conn.openConnection().prepareStatement("SELECT * FROM suite_tests");
		ResultSet resObj = prepStatement.executeQuery();
		while(resObj.next()) {
			MyTest myTest = new MyTest();
			myTest.setName(resObj.getString("name"));
			list.add(myTest);
        }
		
		} catch (Exception sqlException) {
			logger.error(sqlException.getMessage(), sqlException);
		}
		logger.info("UnitTestService.listAll :  unit_tests retrieved.");
		return list;
	}

}
