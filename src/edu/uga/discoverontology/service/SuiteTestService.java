package edu.uga.discoverontology.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import edu.uga.discoverontology.datastore.MySQLConnection;
import edu.uga.discoverontology.model.MyTestSuite;
import edu.uga.discoverontology.presentation.SystemTestNew;

public class SuiteTestService {
	
	final static Logger logger = Logger.getLogger(SystemTestNew.class);
	
	public ArrayList<MyTestSuite> listAll() {
		
		ArrayList<MyTestSuite> list = new ArrayList<>();
		MySQLConnection conn = new MySQLConnection();
		try {

		PreparedStatement prepStatement = conn.openConnection().prepareStatement("SELECT * FROM suite_tests");
		ResultSet resObj = prepStatement.executeQuery();
		while(resObj.next()) {
			MyTestSuite myTestSuite = new MyTestSuite();
			myTestSuite.setName(resObj.getString("name"));
			list.add(myTestSuite);
        }
		
		} catch (Exception sqlException) {
			logger.error(sqlException.getMessage(), sqlException);
		}
		logger.info("SuiteTestService.listAll :  suite_tests retrieved.");
		return list;
	}

}
