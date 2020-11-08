package com.world.mangan.MyConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.world.mangan.Model.Comment;

public class CommentDAO {
	static Connection conn = MyConnection.TestConnect();

	public static void addComment(int id, String name, String comment) {
		String sql = "INSERT INTO COMMENT VALUES('" + name + "','" + comment + "'," + id +","+java.time.LocalDate.now().toString()+")";
		try {
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<Comment> getComment(int id) {
		ArrayList<Comment> arr = new ArrayList<Comment>();
		String sql = "SELECT * FROM COMMENT WHERE ID_MANGA=" + id;
		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Comment cmt = new Comment();
				cmt.setName(rs.getString(1));
				cmt.setComment(rs.getString(2));
				cmt.setDay(rs.getString(4));
				arr.add(cmt);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;

	}
}
