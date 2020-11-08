package com.world.mangan.MyConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.world.mangan.Model.Manga;

public class PageDAO {
	static Connection con = MyConnection.TestConnect();
	public static int HowManyMangaIntoPage(int ManyMangainPage) {
		int count = 0;
		String sql = "SELECT * FROM GET_MANGA_BASIC";
		
		try {
			Statement stm  = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (int)Math.ceil((double)count/ManyMangainPage);
	}
	
	public static ArrayList<Manga> getMangaBasic(int page) {
		ArrayList<Manga> arr = new ArrayList<Manga>();
		String sql = "SELECT * FROM GET_MANGA_BASIC ORDER BY TITLE OFFSET (3*"+page+")" + " ROWS FETCH NEXT 3 ROWS ONLY" ;
		try {
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Manga manga = new Manga();
				manga.setId(rs.getInt("ID_MANGA"));
				manga.setTitle(rs.getString("TITLE"));
				manga.setImg(rs.getString("IMG"));
				manga.setHref(rs.getString("HREF"));
				manga.setDes(rs.getString("DESCRIPTIONS"));
				arr.add(manga);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
}
