package com.world.mangan.MyConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.world.mangan.Model.Manga;

public class FollowDAO {
	static Connection conn = MyConnection.TestConnect();
	
	public static void followManga(int idUser,int idManga,String follow) {
		String str = follow+" "+idManga;
		System.out.println(str);
		String sql = "UPDATE USERS SET FOLLOW='"+str+ "' WHERE Id="+idUser;
		try {
			Statement stm = conn.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteMangaFollow(int idUser,String follow) {
		String sql = "UPDATE USERS SET FOLLOW='"+follow+"' WHERE Id="+idUser;
		try {
			Statement stm = conn.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getIdMangaFollow(int idUser) {
		String str = "";
		String sql = "SELECT FOLLOW FROM USERS WHERE Id="+idUser;
		try {
			Statement stm = conn.createStatement();
			 ResultSet rs = stm.executeQuery(sql);
			 while(rs.next()) {
				 str = rs.getString("Follow");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	

	
	public static Manga getMangaBasicByid(int idManga){
		Manga manga = new Manga();
		String sql = "SELECT * FROM GET_MANGA_BASIC WHERE ID_MANGA="+idManga;
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				manga.setTitle(rs.getString("TITLE"));
				manga.setImg(rs.getString("IMG"));
				manga.setHref(rs.getString("HREF"));
				manga.setDes(rs.getString("DESCRIPTIONS"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manga;
	}
	
	public static ArrayList<String> imageRandom(){
		ArrayList<String> arr = new ArrayList<String>();
		Random generator = new Random();
		int random1 = generator.nextInt(20)+1;
		int random2 = generator.nextInt(20)+1;
		int random3 = generator.nextInt(20)+1;
		while(random1==random2 || random2==random3 || random1==random3) {
			 random1 = generator.nextInt(20)+1;
			 random2 = generator.nextInt(20)+1;
			 random3 = generator.nextInt(20)+1;
		}
		String sql = "SELECT IMG FROM RAMDOM_IMG WHERE id="+random1+" OR id="+random2+" OR id="+random3;
		
		Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				arr.add(rs.getString("IMG"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
}
