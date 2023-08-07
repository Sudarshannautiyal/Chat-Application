package com.brainmentors.chat.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//this class will perform CRUD operations on user
import com.brainmentors.chat.dto.UserDTO;
import com.brainmentors.chat.utils.Encryption;

public class UserDAO {
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String SQL = "Select userid from users where userid =? and password = ?";
		try {
			con=commonDAO.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String encryptedpwd = Encryption.passwordEncrypt(new String(userDTO.getPassword()) );
			pstmt.setString(2, encryptedpwd);
			rs = pstmt.executeQuery();
			return rs.next();
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
	
	//public int add(String userid, String password, byte age, String city, String phone, String email, String country, String state,String areaCode, String stdcode) {
		public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException , Exception{
			System.out.println(" Rec "+userDTO.getUserid()+" "+userDTO.getPassword());
			Connection connection = null;
			Statement stmt = null;
			try {//guarded region
			connection = commonDAO.createConnection();//step 1-connection create
			//step2- we do query
			stmt = connection.createStatement();
			//insert into users(userid,password) values('ram','ram123');
			int record = stmt.executeUpdate("insert into users(userid, password) values('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");
			return record;
			
			}
			finally { //always works wheather exceptions comes or not
				if(stmt!=null) {
				
			         stmt.close();
				}
				//we can do insert, update, delete through this method.
				if(connection!=null) {
			      connection.close();
				}
			}
			
	}

}
