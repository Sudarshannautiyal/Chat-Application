package com.brainmentors.chat.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import static com.brainmentors.chat.utils.ConfigReader.getValue;;

public interface commonDAO {
	public static  Connection createConnection() throws ClassNotFoundException, SQLException {
		//load the driver
		Class.forName(getValue("DRIVER"));
		//Making a Connection
		final String CONNECTION_STRING = getValue("CONNECTION_STRING");
		final String USER_ID = getValue("USER_ID");
		final String PASSWORD = getValue("PASSWORD");
		Connection con = DriverManager.getConnection(CONNECTION_STRING,USER_ID, PASSWORD );
		if(con!=null) {
			System.out.println(" Connection Created....");
			//con.close();
			
		}
		return con;
		
		
		
	}
	

}

