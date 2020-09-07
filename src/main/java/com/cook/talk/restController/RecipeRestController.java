package com.cook.talk.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cook.talk.model.dao.RecipeDAO;
import com.cook.talk.model.service.RecipeService;

@RestController
public class RecipeRestController {

	@Autowired(required = false)
	RecipeDAO recipeDAO;

	@Autowired(required = false)
	RecipeService recipeService;

	@PostMapping("/chosung")
	public List<String> chosung(int cs) {
		// 초성별 재료리스트
		List<String> ingrs = recipeService.ingrNameList(cs);
		return ingrs;
	}

	@PostMapping("/searched")
	public List<String> searched(String ingrName) {
		System.out.println(ingrName);
		System.out.println(recipeService.getSearchedIngrName(ingrName));
		return recipeService.getSearchedIngrName(ingrName);
	}

}
