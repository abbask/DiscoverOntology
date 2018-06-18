package edu.uga.discoverontology.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import edu.uga.discoverontology.datastore.MySQLConnection;
import edu.uga.discoverontology.model.MyTestSystem;
import edu.uga.discoverontology.presentation.SystemTestNew;

public class SystemTestService {
	final static Logger logger = Logger.getLogger(SystemTestNew.class);
	
	public void Add(String Name, String EndPoint, String Graph ) {
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		
		try {
			c.setAutoCommit(false);
			Statement stmtObj = c.createStatement();

			String query = "INSERT INTO system_tests (Name,Endpoint, Graph) VALUES ('" + Name + "','" + EndPoint + "', '" + Graph + "' )";
			stmtObj.executeUpdate(query); 

			c.commit();
			logger.info("SystemTestService.add : new system_test commited.");
		} catch (Exception sqlException) {
			try {
				c.rollback();
				logger.info("SystemTestService.add : new system_test is rolled back.");
			} catch (SQLException e) {
//				logger.info("SystemTestService.add : error in rolling back.");
				logger.error(e.getMessage(), e);
			}
//			logger.info("SystemTestService.add : error in committing.");
			logger.error(sqlException.getMessage(), sqlException);
		}
	}
	
	public ArrayList<MyTestSystem> listAll() {
		
		ArrayList<MyTestSystem> list = new ArrayList<>();
		MySQLConnection conn = new MySQLConnection();
		try {

		PreparedStatement prepStatement = conn.openConnection().prepareStatement("SELECT * FROM system_tests");
		ResultSet resObj = prepStatement.executeQuery();
		while(resObj.next()) {
			MyTestSystem myTestSystem = new MyTestSystem();
			myTestSystem.setID(resObj.getInt("ID"));
			myTestSystem.setName(resObj.getString("name"));
			myTestSystem.setEndPoint(resObj.getString("endpoint"));
			myTestSystem.setGraph(resObj.getString("graph"));
			
			list.add(myTestSystem);
        }
		
		} catch (Exception sqlException) {
//			sqlException.printStackTrace();
			logger.error(sqlException.getMessage(), sqlException);
		}
		logger.info("SystemTestService.listAll :  system_tests retrieved.");
		return list;
	}

}
