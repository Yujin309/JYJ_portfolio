package com.cook.talk.controller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cook.talk.model.VO.IngrVO;
import com.cook.talk.model.VO.ViewsVO;
import com.cook.talk.model.dao.RecipeDAO;
import com.cook.talk.model.dto.RecipeDTO;
import com.cook.talk.model.service.RecipeService;
import com.cook.talk.util.FileTrancefer;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@Autowired
	RecipeDAO recipeDAO;

	@GetMapping("/ingrSelect")
	public String refrigeratorSearch(Model model) {
		model.addAttribute("ingrs", recipeDAO.getIngrName("가", "나"));
		System.out.println(recipeDAO.getIngrName("가", "나"));
		return "refrigerator/ingrSelect";
	}

	
	@PostMapping("/rcmmRecipe")
	public String rcmmRecipe(Model model, @RequestParam(value="selectedIngr", required=false) List<String> selectedIngr, IngrVO ingrVO){
		
		model.addAttribute("rcmmList", recipeDAO.getRcmmList(selectedIngr));

		return "refrigerator/rcmmRecipe";
	}
	
	@GetMapping("/ingrDetail")
	public String getIngrDetail(Model model) {
		List<IngrVO> ingrDetail = recipeService.getIngrDetail();
		model.addAttribute("ingrDetail", ingrDetail);
		
		log.info(recipeService.getIngrDetail());
		
		return "recipe/ingrDetail";
	}
	

	@GetMapping("recipe/newList")
	public String getRecipeList(Model model, ViewsVO viewsVO) {
		List<RecipeDTO> recipeList = recipeService.getRecipeList();
		List<RecipeDTO> recipeList2 = recipeDAO.getRcpListPaiging(1);
		model.addAttribute("recipeList", recipeList);
		model.addAttribute("recipeList2", Math.ceil(recipeDAO.recipeCount() / 20.0));
		model.addAttribute("recipeCount", recipeDAO.recipeCount());
		//레시피 조회이력 저장
		System.out.println(recipeList2);
				
		return "recipe/newList";
	}
	
	@GetMapping("recipe/rankD")
	public String getRankD(Model model) {
		model.addAttribute("getRankD", recipeDAO.getRankD());
		log.info(recipeDAO.getRankD());
		
		return "recipe/rankListD";
	}
	
	@GetMapping("recipe/rankW")
	public String getRankW(Model model) {
		model.addAttribute("getRankW", recipeDAO.getRankW());
		log.info(recipeDAO.getRankW());

		return "recipe/rankListW";
	}
	@GetMapping("recipe/rankM")
	public String getRankM(Model model) {
		model.addAttribute("getRankM", recipeDAO.getRankM());
		log.info(recipeDAO.getRankM());
		
		return "recipe/rankListM";
	}

	@GetMapping("recipe/view") 
	public String getRecipeView(String rcpCode, Model model) {
		model.addAttribute("recipeDTO", recipeDAO.selectRcptpView(rcpCode));
		log.info(recipeDAO.selectRcptpView(rcpCode));
		model.addAttribute("rcpIngrView", recipeDAO.selectRcpIngrView(rcpCode));
		log.info(recipeDAO.selectRcpIngrView(rcpCode));
		model.addAttribute("rcpOrderView", recipeDAO.selectRcpOrderView(rcpCode));
		log.info(recipeDAO.selectRcpOrderView(rcpCode));
		model.addAttribute("tagView", recipeDAO.SelectTagView(rcpCode));
		log.info(recipeDAO.SelectTagView(rcpCode));
		recipeService.rcpViewsUpdate(rcpCode); //조회수 증가
		return "recipe/recipeView";

}

	@GetMapping("recipe/insert")
	public String insertRecipe(@ModelAttribute RecipeDTO recipeDTO) {
		return "recipe/insertRecipe";
	}

		
	@PostMapping("recipe/insertProc")
	public String insertRecipeProc(MultipartHttpServletRequest multi, Principal principal, 
			boolean registerStatus, @ModelAttribute RecipeDTO recipeDTO, String userId) {
		
		recipeDTO.setUserId(principal.getName());
		/* recipeDTO.getRecipeVO().setUserId(principal.getName()); */
		BasicConfigurator.configure(); //log4j 오류처리
		FileTrancefer.requestMultiFilesTrancefer(multi,recipeDTO); 
		  
		  recipeService.insertRecipeProc(registerStatus, recipeDTO, userId);
		 
		return "redirect:/recipe/newList";
	}

	
	
	@GetMapping("recipe/update/{rcpCode}")
	public String updateRecipeProc(@PathVariable String rcpCode, Principal pricipal, RecipeDTO recipeDTO, Model model) {
		model.addAttribute("recipeDTO", recipeDAO.selectRcptpView(rcpCode));
		log.info(recipeDAO.selectRcptpView(rcpCode));
		model.addAttribute("rcpIngrView", recipeDAO.selectRcpIngrView(rcpCode));
		log.info(recipeDAO.selectRcpIngrView(rcpCode));
		model.addAttribute("rcpOrderView", recipeDAO.selectRcpOrderView(rcpCode));
		log.info(recipeDAO.selectRcpOrderView(rcpCode));
		model.addAttribute("tagView", recipeDAO.SelectTagView(rcpCode));
		log.info(recipeDAO.SelectTagView(rcpCode));
		
		return "/recipe/updateRecipe";
	}		
	
	@PostMapping("recipe/updateProc")
	public String updateRecipe(@ModelAttribute RecipeDTO recipeDTO, Model model) {
	
		recipeDAO.updateRecipeProc(recipeDTO);
		
		return "redirect:/recipe/newList";
	}


	

}

