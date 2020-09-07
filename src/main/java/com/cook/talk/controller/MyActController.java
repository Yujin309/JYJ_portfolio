package com.cook.talk.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cook.talk.model.VO.TalkComVO;
import com.cook.talk.model.dao.MypageDAO;
import com.cook.talk.model.dto.MypageDTO;
import com.cook.talk.model.service.MypageService;

@Controller
public class MyActController {

	@Autowired
	MypageDAO mypageDAO;
	
	@Autowired
	MypageService mypageService;

	//활동-즐겨찾기-내가 작성한 글
	@GetMapping("/mypage/myTalk")
	public String myActivity(Model model, MypageDTO mypageDTO, Principal principal) {
		mypageDTO.setUserId(principal.getName());
		
		List<MypageDTO> lists = mypageDAO.getMyTalk(principal.getName());
		
		model.addAttribute("myTalk", lists);
		System.err.println(lists);
		return "/mypage/myTalk";
	}
	
	//활동-즐겨찾기-내가 작성한 댓글(레시피에 단 댓글 + 토크에 단 댓글)
	@GetMapping("/mypage/myCom")
	public String myCom(Model model, MypageDTO mypageDTO, Principal principal) {
		mypageDTO.setUserId(principal.getName());
		
		List<MypageDTO> lists = mypageDAO.getAllMyCom(principal.getName());
		
		model.addAttribute("myAllCom", lists);
		System.err.println(lists);
		/*
		 * model.addAttribute("myTalkCom", mypageDAO.getMyTalkCom());
		 * model.addAttribute("myRcpCom", mypageDAO.getMyRcpCom());
		 */
		return "/mypage/myCom";
	}
	
	
	
}
