package com.world.mangan.MyConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.world.mangan.Model.Manga;

public class MangaDAO {
	static Connection conn = MyConnection.TestConnect();
	//COUNT VIEW MANGA 
	public static void countView(int idManga,int count) {
		String sql = "UPDATE MANGA SET Views="+(count+1)+" WHERE ID="+idManga;
		try {
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getCountMoment(int idManga) {
		int count = 0;
		String sql ="SELECT Views FROM MANGA WHERE ID="+idManga;
		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("Views");
				System.out.println("Count :" + count);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	// GET ALL CHAP
	public static ArrayList<String> linkChaper(int id) {
		ArrayList<String> arr = new ArrayList<String>();
		String sql = "SELECT ID_CHAPER FROM MANGA_CHAPER WHERE ID_MANGA=" + id;
		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				arr.add(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	//GET IMAGE BY CHAP
	public static ArrayList<String> getMangaByChap(int chap) {
		ArrayList<String> arr = new ArrayList<String>();
		String sql = "SELECT * FROM MANGA_CHAPER WHERE ID_CHAPER=" + chap;
		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				for (int i = 3; i <= 32; i++) {
					if (rs.getString(i) != null) {
						arr.add(rs.getString(i));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	// GET NETTRUYEN
	public static ArrayList<Manga> getMangaBasic() {
		ArrayList<Manga> arr = new ArrayList<Manga>();
		String sql = "SELECT * FROM GET_MANGA_BASIC ORDER BY TITLE OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY; ";
		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Manga manga = new Manga();
				manga.setTitle(rs.getString(1));
				manga.setImg(rs.getString(2));
				manga.setHref(rs.getString(3));
				manga.setDes(rs.getString(4));
				arr.add(manga);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public static Manga GetInfomationManga(int id) throws SQLException {
		Manga manga = new Manga();
		String sql ="SELECT NAME_MANGA,DESCRIPTIONS,IMAGE,HREF,NUMBER_CHAPER FROM MANGA WHERE ID="+id;
		
		Statement stmt = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			manga.setTitle(rs.getString("NAME_MANGA"));
			manga.setDes(rs.getString("DESCRIPTIONS"));
			manga.setImg(rs.getString("IMAGE"));
			manga.setHref(rs.getString("HREF"));
			manga.setChap(rs.getInt("NUMBER_CHAPER"));
		}
		return manga;
	}
}
