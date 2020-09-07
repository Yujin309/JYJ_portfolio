package com.cook.talk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cook.talk.model.dao.AdRecipeDAO;
import com.cook.talk.model.dao.AdUserDAO;
import com.cook.talk.model.dao.MainDAO;
import com.cook.talk.model.dao.QnADAO;
import com.cook.talk.model.service.MainService;
import com.cook.talk.model.service.MypageService;

@Controller
public class MainController {

	@Autowired(required = false)
	private MainService mainService;
	@Autowired(required = false)
	private QnADAO qnADao;
	@Autowired(required = false)
	private MainDAO maindao;
	@Autowired(required = false)
	private AdUserDAO aduserDAO;

	@Autowired(required = false)
	private AdRecipeDAO adRecipeDao;

	@Autowired(required = false)
	private MypageService myPageService;

  
	@GetMapping("/index")
	public String loginIndex(Model model, String userId) {
		/* model.addAttribute("rcpList", mainService.recipeList(userId)); */
		model.addAttribute("newRcpList", maindao.newRecipeList());
		return "/main/loginIndex";
	}

	@PostMapping("/qnAGo")
	public String qnaGo() {
		return "/admin/complain";
	}

	@GetMapping("/adminMain")
	public String selectqna(Model model) {
		model.addAttribute("qnaList", qnADao.selectQna());
		model.addAttribute("IngrList", Math.ceil(aduserDAO.countPaginationUser() / 20.0));

		return "admin/adminMain";
	}

	@GetMapping("/recipeSearch/{status}/{page}")
	public String recipeSearch(Model model, String searchWord, @PathVariable String status, @PathVariable int page) {
		model.addAttribute("searchRecipe", maindao.recipeSearch(searchWord, status, (page - 1) * 20));
		model.addAttribute("totalRecipe", maindao.countRecipe(searchWord));
		model.addAttribute("totalPage", (Math.ceil((maindao.countRecipe(searchWord) / 20.0))));
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("status", status);

		return "/recipe/recipeSearch";
	}

	@GetMapping("/searchBichart")
	public String biSearch(Model model, String rcpCode) {
		model.addAttribute("rcpCode", adRecipeDao.searchRcpByRcpCode(rcpCode));
		return "/admin/biSearch";
	}

	@GetMapping("/googleChart/{rcpCode}")
	public String googleChart(Model model, @PathVariable String rcpCode) {

		model.addAttribute("gender", maindao.selectGender(rcpCode));
		model.addAttribute("birth", maindao.selectBirth(rcpCode));
		model.addAttribute("month", maindao.selectMonth(rcpCode));
		return "/main/chart";
	}
}
