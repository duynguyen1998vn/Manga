package com.world.mangan.MyController;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.world.mangan.MyConnection.CommentDAO;
import com.world.mangan.MyConnection.MangaDAO;
import com.world.mangan.MyConnection.PageDAO;

@Controller
public class HomeController {

	@GetMapping(value = { "/", "/home"})
	private String home(Model model) {
		model.addAttribute("LOGIN","LOGIN");
		model.addAttribute("PAGE",PageDAO.HowManyMangaIntoPage(3));
		model.addAttribute("MANGA_BASIC", PageDAO.getMangaBasic(0));
		model.addAttribute("FOLLOW","FOLLOW");
		
		return "home";
	}

	@GetMapping(value="/home/{page}")
		private String demooo(@PathVariable("page") int page,Model model) {
		model.addAttribute("PAGE",PageDAO.HowManyMangaIntoPage(3));
		model.addAttribute("MANGA_BASIC",PageDAO.getMangaBasic(page-1));
		model.addAttribute("FOLLOW","FOLLOW");
		return "home";
	}
	
	

	@GetMapping(value = "/onepiece")
	private String onePiece(Model model) {
		MangaDAO.countView(1, MangaDAO.getCountMoment(1));
		model.addAttribute("IdManga", 1);
		model.addAttribute("TITLE", "One Piece");
		model.addAttribute("IMAGE", "https://wallpaperaccess.com/full/17352.jpg");
		model.addAttribute("CHAPER", MangaDAO.linkChaper(1).size());
		model.addAttribute("COMMENT", CommentDAO.getComment(1));
		return "manga";
	}

	@GetMapping(value = "/aqua")
	private String aqua(Model model) {
		model.addAttribute("IdManga", 2);
		model.addAttribute("TITLE", "Kono Subarashii Sekai Ni Shukufuku Wo");
		model.addAttribute("IMAGE", "https://images3.alphacoders.com/108/1080053.png");
		model.addAttribute("CHAPER", MangaDAO.linkChaper(2).size());
		model.addAttribute("COMMENT", CommentDAO.getComment(2));
		return "manga";
	}

	@GetMapping(value = "/umaru")
	private String umaru(Model model) {
		model.addAttribute("IdManga", 3);
		model.addAttribute("TITLE", "Himouto Umaru");
		model.addAttribute("IMAGE", "https://vn-test-11.slatic.net/p/be953f7f25153a3337c4d08c5c8a0b77.jpg");
		model.addAttribute("CHAPER", MangaDAO.linkChaper(3).size());
		model.addAttribute("COMMENT", CommentDAO.getComment(3));
		return "manga";
	}

	/*
	 * @GetMapping(value = "/demo") private void demo(Model model) throws
	 * IOException {
	 * 
	 * Elements image = null; Document doc;
	 * 
	 * String link = "https://bigtruyenz.com/truyen/dua-em-gai-hai-mat/chapter-";
	 * for (int i = 204; i >= 1; i--) { String tempLink = link + i + "/";
	 * System.out.println(tempLink); doc = Jsoup.connect(tempLink).get(); Elements
	 * element = doc.select("img[data-index]");
	 * 
	 * for(int j=0;j<element.size();j++) { String src =
	 * element.get(j).absUrl("src"); System.out.println(src); } } }
	 */

	@PostMapping(value = "/show")
	private String showManga(Model model, HttpServletRequest request) {
		int chaper = Integer.parseInt(request.getParameter("chaper"));
		model.addAttribute("chap", chaper);
		ArrayList<String> arrChap = MangaDAO.getMangaByChap(chaper);
		model.addAttribute("Manga", arrChap);
		return "chap";
	}

	@PostMapping(value = "/next")
	private String next(Model model, HttpServletRequest request) {
		int chaper = Integer.parseInt(request.getParameter("chaper")) + 1;
		model.addAttribute("chap", chaper);
		ArrayList<String> arrChap = MangaDAO.getMangaByChap(chaper);
		model.addAttribute("Manga", arrChap);
		return "chap";
	}

	@PostMapping(value = "/back")
	private String back(Model model, HttpServletRequest request) {
		int chaper = Integer.parseInt(request.getParameter("chaper")) - 1;
		model.addAttribute("chap", chaper);
		ArrayList<String> arrChap = MangaDAO.getMangaByChap(chaper);
		model.addAttribute("Manga", arrChap);
		return "chap";
	}


}
