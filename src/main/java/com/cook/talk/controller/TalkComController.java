package com.cook.talk.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cook.talk.model.VO.TalkComVO;
import com.cook.talk.model.VO.TalkVO;
import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.dao.TalkComDAO;
import com.cook.talk.model.dto.TalkDTO;
import com.cook.talk.model.service.TalkComService;
import com.cook.talk.model.service.TalkService;

@Controller
@RequestMapping("/com")
public class TalkComController {

	@Autowired
	private TalkComService comservice;
	@Autowired
	private TalkService talkservice;

	// 댓글 입력

	@RequestMapping(value = "/comInsert", method = RequestMethod.POST)
	@ResponseBody
	public String create(TalkComVO comVO, Principal principal, RedirectAttributes rttr) {

		rttr.addAttribute("comVO", comVO.getTalkComCode());
		comVO.setUserId(principal.getName());
		String talkCode = comVO.getTalkCode();
		String talkComCode = comVO.getTalkComCode();
		System.err.println(comVO);
		comservice.createCom(comVO);
		return "success";
	}
	
//댓글 수정 
	@PutMapping("/update/{talkComCode}")
	@ResponseBody
	public String updateCom(@PathVariable String talkComCode, @RequestBody String talkCom) {
		comservice.updateCom(talkComCode, talkCom);
		return "success";
	}

	// 댓글 삭제

	@PostMapping("/deletCom/{talkComCode}")
	@ResponseBody
	public String deleteCom(@PathVariable String talkComCode) {
		comservice.deleteCom(talkComCode);
		System.out.println(talkComCode);
		return "success";
	}

//댓글 목록 
	@RequestMapping("/list.do")
	public String listcom(@RequestParam String talkComCode, Model model) {
		model.addAttribute("talkCode", comservice.searchComment(talkComCode));
		return "talk/comTest";
	}

}
