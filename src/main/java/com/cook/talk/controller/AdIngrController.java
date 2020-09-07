package com.cook.talk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cook.talk.model.VO.IngrVO;
import com.cook.talk.model.dao.AdIngrDAO;
import com.cook.talk.model.service.AdIngrService;

@Controller
public class AdIngrController {
	@Autowired(required = false)
	AdIngrDAO adingrDAO;
	@Autowired(required = false)
	AdIngrService adingrService;

	@GetMapping("/admin/adIngrList") // 재료 뿌리기
	public String allSelectIngr(Model model) {
		model.addAttribute("IngrList", adingrDAO.allSelectIngr());
		
		return "admin/adIngrList"; // data를 처리한다. 페이지 이동이 아닌,
	}


	@GetMapping("/admin/insertIngr") // 재료 추가
	public String insertIngr(@ModelAttribute IngrVO ingrVO, Model model) {
		System.out.println(ingrVO);
		/*
		 * model.addAttribute("msg", adingrService.insertIngrImpl(ingrVO);
		 */ System.out.println("추가되었습니다." + ingrVO);
		return "admin/addIngr";
	}// 화면에 뿌려주는

	@PostMapping("/admin/insertIngr") // 사진추가
	public String insertPic(IngrVO ingrVO, Model model, @RequestParam("file") MultipartFile multipartfile) {
		model.addAttribute("msg", "추가되었습니다.");
		ingrVO.setIngrPic(multipartfile.getOriginalFilename());
		adingrService.insertIngrImpl(ingrVO, multipartfile);
		System.out.println(multipartfile + " 컨트롤러 추가된 파일명");
		System.out.println(ingrVO + "컨틀로러 그릇");

		return "admin/addIngr";
	}



}
