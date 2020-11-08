package com.world.mangan.MyController;

import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.world.mangan.Model.Manga;
import com.world.mangan.MyConnection.CommentDAO;
import com.world.mangan.MyConnection.FollowDAO;
import com.world.mangan.MyConnection.LoginDAO;
import com.world.mangan.MyConnection.MangaDAO;
import com.world.mangan.MyConnection.PageDAO;
import com.world.mangan.MyConnection.SearchDAO;

@Controller
public class FeatureController {

	@GetMapping(value = "/login")
	private String formLogin() {
		return "form-login";
	}

	// LOGIN
	@PostMapping(value = "/login")
	private String login(HttpServletRequest request, Model model, HttpSession session) {
		String account = request.getParameter("account");
		String password = request.getParameter("pass");
		int idUser = LoginDAO.login(account, password);
		if (idUser != 0) {
			model.addAttribute("LOGIN", "Log Out");
			session.setAttribute("ID_USER", idUser);
			model.addAttribute("PAGE", PageDAO.HowManyMangaIntoPage(3));
			model.addAttribute("MANGA_BASIC", PageDAO.getMangaBasic(0));
			model.addAttribute("BTN_FOLLOW", "Manga Follow");
			
			return "home";
		} else {
			model.addAttribute("message", "Login Fail!!");
			return "form-login";
		}
	}

	// create new Account
	@PostMapping(value = "/create-account")
	private String createAccount(HttpServletRequest request, Model model) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if (LoginDAO.insertUser(account, password) == true) {
			model.addAttribute("message", "Create SUCCESS");
		} else {
			model.addAttribute("message", "Create FAIL!!");
		}
		return "form-login";
	}

	// Add and Show Comment
	@PostMapping(value = "/add-comment")
	private String addComment(HttpServletRequest request, Model model) {
		CommentDAO.addComment(Integer.parseInt(request.getParameter("Id")), request.getParameter("name_comment"),
				request.getParameter("comment"));

		model.addAttribute("COMMENT", CommentDAO.getComment(Integer.parseInt(request.getParameter("Id"))));
		return "manga";
	}

	// Follow Manga
	@GetMapping(value = "/manga-follow")
	private void follow(HttpServletRequest request, Model model, HttpSession session) {
		int idManga = Integer.parseInt(request.getParameter("idMangaBasic"));
		System.out.println("follow manga id = " + idManga);
		int idUser = (int) session.getAttribute("ID_USER");
		System.out.println("Iduser Follow " + idUser);

		String allIdMangaFollow = FollowDAO.getIdMangaFollow(idUser);
		System.out.println("String follow " + allIdMangaFollow);
		FollowDAO.followManga(idUser, idManga, allIdMangaFollow);

		model.addAttribute("FOLLOW", "UNFOLLOW");
	}

	// UnFollow Manga
	@GetMapping(value = "/unfollow")
	private String unFollow(HttpServletRequest request, HttpSession session) {

		int id_unFollow = Integer.parseInt(request.getParameter("idMangaBasic"));

		String listFollow = FollowDAO.getIdMangaFollow((int) session.getAttribute("ID_USER"));
		ArrayList<Integer> arrInteger = new ArrayList<Integer>();
		String[] arr = listFollow.split(" ");
		for (int i = 0; i < arr.length; i++) {
			arrInteger.add(Integer.parseInt(arr[i]));
		}
		arrInteger.remove(id_unFollow);
		String temp = "";
		for (int i = 0; i < arrInteger.size(); i++) {
			if (i == arrInteger.size() - 1) {
				temp += arrInteger.get(i);
			} else {
				temp = arrInteger.get(i) + " ";
			}
		}
		FollowDAO.deleteMangaFollow((int) session.getAttribute("ID_USER"), temp);
		return "manga-follow";
	}

	// See List Follow
	@GetMapping(value = "/list-follow")
	private String listFollow(HttpSession session, Model model) {

		ArrayList<Manga> arrManga = new ArrayList<Manga>();
		String listFollow = FollowDAO.getIdMangaFollow((int) session.getAttribute("ID_USER"));

		ArrayList<Integer> arrInteger = new ArrayList<Integer>();
		String[] arr = listFollow.split(" ");
		for (int i = 0; i < arr.length; i++) {
			arrInteger.add(Integer.parseInt(arr[i]));
		}
		for (int i = 0; i < arrInteger.size(); i++) {
			arrManga.add(FollowDAO.getMangaBasicByid(arrInteger.get(i)));
		}
		model.addAttribute("MANGA_FOLLOW", arrManga);
		ArrayList<String> arrImage = FollowDAO.imageRandom();
		model.addAttribute("IMAGE1", arrImage.get(0));
		model.addAttribute("IMAGE2", arrImage.get(1));
		model.addAttribute("IMAGE3", arrImage.get(2));
		model.addAttribute("VALUE_BTN_FOLLOW","UNFOLLOW");
		return "manga-follow";
	}

	// Get Manga Hot By View
	@GetMapping(value = "/hot")
	private String hotList(Model model) throws SQLException {
		int max = Integer.MIN_VALUE;
		int id = 0;
		for (int i = 1; i <= 3; i++) {
			if (MangaDAO.getCountMoment(i) > max) {
				max = MangaDAO.getCountMoment(i);
				id = i;
			}
			model.addAttribute("MANGA", MangaDAO.GetInfomationManga(id));
		}
		return "hot-manga";
	}

	// Search
	@GetMapping(value = "/search")
	private String search(HttpServletRequest request, Model model) {
		String work_search = flattenToAscii(request.getParameter("s").toUpperCase().trim());

		ArrayList<String> arrTitle = SearchDAO.allTitleManga();
		ArrayList<Integer> arrIdManga = new ArrayList<Integer>();
		ArrayList<Manga> arrMangaGetById = new ArrayList<Manga>();

		for (int i = 0; i < arrTitle.size(); i++) {
			String temp = flattenToAscii(arrTitle.get(i).trim());

			if (temp.contains(work_search)) {
				arrIdManga.add(SearchDAO.getIdMangaByTitle(arrTitle.get(i)));
			}
		}

		for (int i = 0; i < arrIdManga.size(); i++) {
			System.out.println(arrIdManga.get(i));
			arrMangaGetById.add(FollowDAO.getMangaBasicByid(arrIdManga.get(i)));
		}

		for (int i = 0; i < arrMangaGetById.size(); i++) {
			System.out.println(arrMangaGetById.get(i).getTitle());
			System.out.println(arrMangaGetById.get(i).getImg());
		}

		model.addAttribute("MANGA_FOLLOW", arrMangaGetById);

		ArrayList<String> arrImage = FollowDAO.imageRandom();
		model.addAttribute("IMAGE1", arrImage.get(0));
		model.addAttribute("IMAGE2", arrImage.get(1));
		model.addAttribute("IMAGE3", arrImage.get(2));
		model.addAttribute("VALUE_BTN_FOLLOW","");
		return "manga-follow";
	}

	
	@RequestMapping("/home/category/{Category}")
		private String category(@PathVariable("Category") String category,Model model ) {
	
		model.addAttribute("MANGA_FOLLOW",SearchDAO.getAllMangaByTag(category));
		ArrayList<String> arrImage = FollowDAO.imageRandom();
		model.addAttribute("IMAGE1", arrImage.get(0));
		model.addAttribute("IMAGE2", arrImage.get(1));
		model.addAttribute("IMAGE3", arrImage.get(2));
		model.addAttribute("VALUE_BTN_FOLLOW","");
		return "manga-follow";
	}
	
	
	
	
	
	
	
	
	
	
	public String flattenToAscii(String s) {
		if (s == null || s.trim().length() == 0)
			return "";
		return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
	}
}
