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
	
	public void Add(String name, String assertType, String query, ExpectedValue expectedValue, String  message, int systemTestID) {
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		
		try {
			c.setAutoCommit(false);
			Statement stmtObj = c.createStatement();
			
			

			String queryString = "INSERT INTO unit_tests (name,assertType,query, expected_value_id, message,system_test_id) VALUES ('" + name + "','" + assertType + "','" + query + "', '" + expected_value_id + "','" + message + "'," + systemTestID + ")";
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

		PreparedStatement prepStatement = conn.openConnection().prepareStatement("SELECT u.id,u.name,u.assertType,u.query,u.expectedValue,u.message, u.system_test_id, s.name as system_test_name  FROM unit_tests u Inner join system_tests s on u.system_test_id = s.ID ");
		ResultSet resObj = prepStatement.executeQuery();
		while(resObj.next()) {
			MyUnitTest myUnitTest = new MyUnitTest();
			myUnitTest.setName(resObj.getString("name"));
			myUnitTest.setAssertType(resObj.getString("assertType"));
			myUnitTest.setQuery(resObj.getString("query"));
			myUnitTest.setMessage(resObj.getString("message"));
			
			MyTestSystem systemTest = new MyTestSystem();
			systemTest.setID(resObj.getInt("system_test_id"));
			systemTest.setName(resObj.getString("system_test_name"));
			myUnitTest.setSystemTest(systemTest);
			
			list.add(myUnitTest);
        }
		
		} catch (Exception sqlException) {
			logger.error(sqlException.getMessage(), sqlException);
		}
		logger.info("UnitTestService.listAll :  unit_tests retrieved.");
		return list;
	}
	
	

}
