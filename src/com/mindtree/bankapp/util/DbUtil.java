package com.mindtree.bankapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mindtree.bankapp.exceptions.daoExceptions.BankDaoException;

public class DbUtil {
	
	static Connection con = null;
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/kalinga_bank";
	static String user = "root";
	static String pass = "Welcome123";
	
	static public Connection getConnection() throws BankDaoException {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			throw new BankDaoException("Cannot make connection",e);
		} catch (SQLException e) {
			throw new BankDaoException("Cannot make connection",e);
		}
		
		return con;
	}

}
