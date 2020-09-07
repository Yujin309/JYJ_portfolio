package com.cook.talk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cook.talk.model.dao.AdRecipeDAO;
import com.cook.talk.model.dao.AdUserDAO;

@Controller
public class AdRecipeController {
	@Autowired
	AdRecipeDAO adRecipeDAO;
	@Autowired
	AdUserDAO aduserDAO;

	@GetMapping("/admin/listRcp")
	public String ListRcp(Model model) {

		model.addAttribute("rcpList", adRecipeDAO.allSelectRecipe());
		model.addAttribute("IngrList", Math.ceil(aduserDAO.countPaginationUser() / 20.0));
		System.out.println(adRecipeDAO.allSelectRecipe() + "모두 불러오기 완료");
 
		return "admin/adRecipeList";

	}

}
