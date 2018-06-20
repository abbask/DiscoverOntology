package edu.uga.discoverontology.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uga.discoverontology.datastore.MySQLConnection;
import edu.uga.discoverontology.model.*;
import edu.uga.discoverontology.model.MyUnitTest;
import edu.uga.discoverontology.presentation.SystemTestNew;

public class UnitTestService {
	
	final static Logger logger = Logger.getLogger(UnitTestService.class);
	
	public void Add(String name, String query, String assertType,ExpectedValue[] expectedValues, String  message, int systemTestID) {
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		
		try {
			c.setAutoCommit(false);
			Statement stmtObj = c.createStatement();
			
			String queryString = "INSERT INTO unit_tests (name,query,assert_type,message,system_test_id) VALUES ('" + name + "','" + query + "', '" + assertType + "','" +  message + "'," + systemTestID + ")";
			stmtObj.executeUpdate(queryString); 
			ResultSet rs = stmtObj.getGeneratedKeys();
			int unit_test_id = 0;
            if(rs.next()){
            	unit_test_id=rs.getInt(1);
            }
			
			for(ExpectedValue e : expectedValues) {
				String queryString2 = "INSERT INTO expected_values (subject,predicate,object, unit_test_id) VALUES ('" + e.getSubject() + "','" + e.getPredicate() + "','" + e.getObject() + "', '" + unit_test_id + "')";
				stmtObj.executeUpdate(queryString2); 
			}

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

		PreparedStatement prepStatement = conn.openConnection().prepareStatement("SELECT u.id,u.name,u.query,u.message, u.system_test_id, s.name as system_test_name  FROM unit_tests u Inner join system_tests s on u.system_test_id = s.ID ");
		ResultSet resObj = prepStatement.executeQuery();
		while(resObj.next()) {
			int id = resObj.getInt("id");
			MyUnitTest myUnitTest = new MyUnitTest();
			myUnitTest.setID(resObj.getInt("id"));
			myUnitTest.setName(resObj.getString("name"));
			myUnitTest.setQuery(resObj.getString("query"));
			myUnitTest.setMessage(resObj.getString("message"));
			
			MyTestSystem systemTest = new MyTestSystem();
			systemTest.setID(resObj.getInt("system_test_id"));
			systemTest.setName(resObj.getString("system_test_name"));
			myUnitTest.setSystemTest(systemTest);
			
			PreparedStatement valuesStatement = conn.openConnection().prepareStatement("SELECT 	ID, subject,predicate, object from expected_values where unit_test_id=" + id);
			ResultSet valueRes = valuesStatement.executeQuery();
			ArrayList<ExpectedValue> expectedValues = new ArrayList<>();
			
			while(valueRes.next()) {
				ExpectedValue expectedValue = new ExpectedValue();
				expectedValue.setID(valueRes.getInt("ID"));
				expectedValue.setSubject(valueRes.getString("subject"));
				expectedValue.setPredicate(valueRes.getString("predicate"));
				expectedValue.setObject(valueRes.getString("object"));
				expectedValues.add(expectedValue);
			}
			myUnitTest.setExpectedValues(expectedValues);
			
			list.add(myUnitTest);
        }
		
		} catch (Exception sqlException) {
			logger.error(sqlException.getMessage(), sqlException);
		}
		logger.info("UnitTestService.listAll :  unit_tests retrieved.");
		return list;
	}
	
	

}
