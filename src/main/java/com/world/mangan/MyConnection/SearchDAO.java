package com.world.mangan.MyConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.world.mangan.Model.Manga;

public class SearchDAO {
	static Connection conn = MyConnection.TestConnect();
	
	public static Manga search(String search) {
		Manga manga = new Manga();
		String sql = "SELECT * FROM GET_MANGA_BASIC WHERE TITLE='"+search+"'";
		
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				manga.setTitle(rs.getString("TITLE"));
				manga.setImg(rs.getString("IMG"));
				manga.setHref(rs.getString("HREF"));
				manga.setDes(rs.getString("DESCRIPTIONS"));
				manga.setTag(rs.getString("TAG"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manga;
	}
	
	public static int getIdMangaByTitle(String title) {
		int i =0;
		String sql = "SELECT ID_MANGA FROM GET_MANGA_BASIC WHERE TITLE LIKE '"+title+"%'";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			 while(rs.next()) {
				i=rs.getInt("ID_MANGA");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	

	public static ArrayList<String> allTitleManga(){
		ArrayList<String> arr = new ArrayList<String>();
		String sql = "SELECT * FROM GET_MANGA_BASIC";
		
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				arr.add(rs.getString("TITLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	
	public static ArrayList<Manga> getAllMangaByTag(String tag){
		ArrayList<Manga> arr = new ArrayList<Manga>();
		String sql = "SELECT * FROM GET_MANGA_BASIC WHERE TAG LIKE'"+tag+"%'";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				Manga mg = new Manga();
				mg.setTitle(rs.getString("TITLE"));
				mg.setImg(rs.getString("IMG"));
				mg.setHref(rs.getString("HREF"));
				mg.setDes(rs.getString("DESCRIPTIONS"));
				mg.setTag(rs.getString("TAG"));
				arr.add(mg);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

}
