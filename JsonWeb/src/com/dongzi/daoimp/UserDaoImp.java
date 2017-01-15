package com.dongzi.daoimp;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dongzi.db.GetConn;

public class UserDaoImp {
	public boolean login(String username,String password) 
	{
		boolean b = false;
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from usermsg where username=? and password=?");
			ps.setString(1,username);
			ps.setString(2,password);
			rs=ps.executeQuery();
			if (rs.next())
			{
				b=true;
			}
			else
			{
				b=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public int registerUsers(String username, String password) {

		int row = 0;
		GetConn getConn=new GetConn();
		Connection conn=getConn.getConnection();
		try {
			/*PreparedStatement ps = conn.prepareStatement("insert into usermsg set userName=?,password=?");
			ps.setString(1,username);
			ps.setString(2,password);*/
			String sql = "INSERT INTO usermsg(userName,password)values(?,?)";  
			PreparedStatement ps = conn.prepareStatement(sql);  
			ps.setString(1, username);  
			ps.setString(2, password);  
			row = ps.executeUpdate();  
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	

	

}
