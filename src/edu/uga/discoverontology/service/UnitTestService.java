package edu.uga.discoverontology.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import edu.uga.discoverontology.datastore.MySQLConnection;
import edu.uga.discoverontology.model.*;
import edu.uga.discoverontology.model.MyUnitTest;
import edu.uga.discoverontology.presentation.SystemTestNew;

public class UnitTestService {
	
	final static Logger logger = Logger.getLogger(UnitTestService.class);
	
	public void Add(String name, String assertType, String query, String expectedValue, String  message) {
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		
		try {
			c.setAutoCommit(false);
			Statement stmtObj = c.createStatement();

			String queryString = "INSERT INTO unit_tests (name,assertType,query, expectedValue, message) VALUES ('" + name + "','" + assertType + "','" + query + "', '" + expectedValue + "','" + message + "')";
			stmtObj.executeUpdate(queryString); 

			c.commit();
			logger.info("UnitTestService.add : new unit_test commited.");
		} catch (Exception sqlException) {
			try {
				c.rollback();
				logger.info("UnitTestService.add : new unit_test is rolled back.");
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			logger.error(sqlException.getMessage(), sqlException);
		}
	}
	
	public ArrayList<MyUnitTest> listAll() {
		
		ArrayList<MyUnitTest> list = new ArrayList<>();
		MySQLConnection conn = new MySQLConnection();
		try {

		PreparedStatement prepStatement = conn.openConnection().prepareStatement("SELECT * FROM unit_tests");
		ResultSet resObj = prepStatement.executeQuery();
		while(resObj.next()) {
			MyUnitTest myUnitTest = new MyUnitTest();
			myUnitTest.setName(resObj.getString("name"));
			myUnitTest.setAssertType(resObj.getString("assertType"));
			myUnitTest.setQuery(resObj.getString("query"));
			myUnitTest.setExpectedValue(resObj.getString("expectedValue"));
			myUnitTest.setMessage(resObj.getString("message"));
			list.add(myUnitTest);
        }
		
		} catch (Exception sqlException) {
			logger.error(sqlException.getMessage(), sqlException);
		}
		logger.info("UnitTestService.listAll :  unit_tests retrieved.");
		return list;
	}
	
	

}
