package edu.uga.discoverontology.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import edu.uga.discoverontology.datastore.MySQLConnection;

public class SystemHistoryService {
	final static Logger logger = Logger.getLogger(SystemHistoryService.class);

	public int Add(int system_test_id) {
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		int system_test_history_id= 0;
		try {
			c.setAutoCommit(false);
			Statement stmtObj = c.createStatement();
			
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			String query = "INSERT INTO system_test_history (system_test_id,date) VALUES (" + system_test_id + ",'" + sqlDate + "')";
			stmtObj.executeUpdate(query); 
			
			ResultSet rs = stmtObj.getGeneratedKeys();
            if(rs.next()){
            	system_test_history_id=rs.getInt(1);
            }
            
            

			c.commit();
			conn.closeConnection();
			logger.info("HistoryService.add : new History commited.");
		} catch (Exception sqlException) {
			try {
				c.rollback();
				conn.closeConnection();
				logger.info("HistoryService.add : new History is rolled back.");
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			logger.error(sqlException.getMessage(), sqlException);
		}
		conn = null;
		return system_test_history_id;
	}
}
