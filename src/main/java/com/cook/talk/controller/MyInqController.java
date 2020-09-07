package com.cook.talk.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cook.talk.model.VO.QnAVO;
import com.cook.talk.model.dao.MypageDAO;
import com.cook.talk.model.service.MypageService;

@Controller
public class MyInqController {

	@Autowired
	MypageDAO mypageDAO;
	
	@Autowired
	MypageService mypageService;
	
	
	//마이페이지-문의사항-문의사항 입력하기
	@GetMapping("/mypage/myInquiry")
	public String myInquiry(Model model, String qnaTitle, String qnaCont) {
	
		return "/mypage/myInquiry";
	}
	
	
	
	//마이페이지-문의사항-나의 문의사항 INSERT 처리
	@PostMapping("/mypage/inputInq")
	public String myInquiryProc(@ModelAttribute QnAVO qnAVO, Model model, Principal principal) {
		qnAVO.setUserId(principal.getName());
		mypageService.rqMyInq(qnAVO);
	
		return "redirect:/mypage/myInquiryList";
	}
	
	
	@GetMapping("/mypage/myInquiryList")
	public String myInquiryList(Model model, Principal principal, QnAVO qnAVO) {
		
		qnAVO.setUserId(principal.getName());
		System.err.println(qnAVO);
		
		List<QnAVO> lists = mypageDAO.getmyInq(principal.getName());
		model.addAttribute("myInqList", lists);
		return "/mypage/myInquiryList";
	}
	
}
