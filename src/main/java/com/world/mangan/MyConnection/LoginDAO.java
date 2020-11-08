package com.world.mangan.MyConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {
	static Connection conn = MyConnection.TestConnect();

	public static int login(String account, String pass) {
		int id = 0;
		String sql = "SELECT * FROM USERS WHERE USERS.USERNAME='" + account + "' AND USERS.PASS='" + pass + "'";

		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Khong tim Thay User");
		}
		return id;
	}
	
	public static boolean insertUser(String account , String password) {
		String sql ="INSERT INTO USERS VALUES('"+account+"','"+password+"')";
		try {
			Statement stm = conn.createStatement();
			stm.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Create New Account Fail");
			return false;
		}
		
	}
}
