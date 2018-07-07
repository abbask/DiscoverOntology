package edu.uga.discoverontology.datastore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

public class MySQLConnection {
	
	final static Logger logger = Logger.getLogger(MySQLConnection.class);
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/discover_ontology?useUnicode=yes&characterEncoding=UTF-8"; //http://localhost/phpmyadmin/
	
	static final String JDBC_USER = "root";
	static final String JDBC_PASS = "";
	
	Connection connObj;
	
	public MySQLConnection() {
		connObj = null;
	}

	public Connection openConnection() {
		
		try {
			Class.forName(JDBC_DRIVER);  
			connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			logger.error("MySQLConnection: sqlException error - Connection error");			
		}
		return connObj;
	}
}
