package com.cook.talk.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cook.talk.model.dao.MypageDAO;
import com.cook.talk.model.dto.MypageDTO;
import com.cook.talk.model.service.MypageService;

@Controller
public class MyFreqController {

	@Autowired
	MypageDAO mypageDAO;
	
	@Autowired
	MypageService mypageService;
	
	//마이페이지-즐겨찾기-내가 팔로우한 쉐프
	@GetMapping("/mypage/myFollow")
	public String myFrequent(Model model, MypageDTO mypageDTO, Principal principal) {
		
		mypageDTO.setUserId(principal.getName());
		System.err.println(mypageDTO);
		
		List<MypageDTO> lists = mypageDAO.getMyFollow(principal.getName());
		
		model.addAttribute("myFollow", lists);
		System.out.println(lists);
		return "/mypage/myFollow";
	}
	
	//마이페이지-즐겿자기-내가 스크랩한 레시피
	@GetMapping("/mypage/myScraped")
	public String myScraped(Model model, MypageDTO mypageDTO, Principal principal) {
		mypageDTO.setUserId(principal.getName());
		System.err.println(mypageDTO);
		
		List<MypageDTO> lists = mypageDAO.getMyScrapedRecipe(principal.getName());
		
		model.addAttribute("myScraped", lists);
		System.out.println(lists);
		return "/mypage/myScraped";
	}

	
}
