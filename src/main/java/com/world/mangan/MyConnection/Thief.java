package com.world.mangan.MyConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Thief {
	static Connection conn = MyConnection.TestConnect();
	
	//
	public static void insertData(int i) {
		String sql = "INSERT INTO MANGA_CHAPER(ID_MANGA,ID_CHAPER) VALUES (1," + i + ")";
		try {
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Okkk" + i);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//GET all chap
	public static ArrayList<String> getLinkChaper(String URL) throws IOException {
		ArrayList<String> arrChaper = new ArrayList<String>();
		Document doc = Jsoup.connect(URL).get();
		Elements chaper = doc.select("span.title");
		Elements a = chaper.select("a");
		for (int i = 0; i < a.size(); i++) {
			String href = a.get(i).absUrl("href");
			arrChaper.add(href);
		}
		return arrChaper;
	}
	
	//GET Image
	public static ArrayList<String> getImage(String URL) throws IOException {
		ArrayList<String> arrImage = new ArrayList<String>();
		Document doc = Jsoup.connect(URL).get();
		Elements image = (Elements) doc.select("img[class=bbCodeImage LbImage]");

		for (int i = 0; i < image.size(); i++) {
			String src = image.get(i).absUrl("src");
			arrImage.add(src);
			System.out.println(src);
		}
		return arrImage;
	}

	//Insert IMAGE IN CHAP
	public static void updateManga(int chap, int IMG, String img) {
		String sql = "UPDATE MANGA_CHAPER SET IMG" + (IMG + 1) + "='" + img + "' WHERE ID_CHAPER=" + chap;
		try {
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("success chap: " + chap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
