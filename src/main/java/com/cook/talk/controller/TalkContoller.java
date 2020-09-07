package com.cook.talk.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cook.talk.model.VO.TalkVO;
import com.cook.talk.model.dao.TalkDAO;
import com.cook.talk.model.dto.RecipeDTO;
import com.cook.talk.model.service.TalkService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/talk/*")
@Controller
@AllArgsConstructor
public class TalkContoller {
	@Autowired
	private TalkService talkservice;
	private TalkDAO talkDAO;

//목록 
	@GetMapping("/list")
	public String list(Model model) {
	//	List<TalkVO> talkList2 = talkDAO.getTalkListPaiging(1);
		model.addAttribute("list", talkservice.getTalkList());
	//	model.addAttribute("talkList2", Math.ceil(talkDAO.talkCount()/ 10.0));
		model.addAttribute("talkCount", talkDAO.talkCount());
		return "talk/list";
	}

//등록
	@RequestMapping(value = "/insert", method = { RequestMethod.POST, RequestMethod.GET })
	public String insert(@ModelAttribute TalkVO talkVO) {
		String msg = "talk/wirte";
		return msg;
	}

	// 등록 버튼
	@PostMapping("/insert.do")
	public String insertTalk(Principal principal, TalkVO talkVO) {
		talkVO.setUserId(principal.getName());
		talkservice.insert(talkVO);
		return "redirect:/talk/list";
	}

	// 상세 페이지 이동
	@RequestMapping(value = "/detail/{talkCode}", method = RequestMethod.GET)
	public String detail(@PathVariable String talkCode, Model model) {
		model.addAttribute("talkCode", talkservice.detail(talkCode));
		TalkVO talk = talkservice.getBoardById(talkCode);
		System.err.println(talk);
		model.addAttribute("listCom", talk);
		return "talk/talkDetail";
	}

	// 수정페이지 이동
	@RequestMapping(value = "/updatePage", method = RequestMethod.GET)
	public String updatePage(TalkVO talkVO, Model model) {
		System.err.println(talkVO);
		TalkVO talk = talkservice.detail(talkVO.getTalkCode());
		System.err.println(talk);
		model.addAttribute("talkupdate", talk);
		return "talk/update";
	}
	
	//수정하기
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateTalk(Principal principal, TalkVO talkVO) {
		System.err.println(talkVO);
		talkVO.setUserId(principal.getName());		
		talkservice.updateTalk(talkVO);
		return "redirect:/talk/list";
	}

	
	
//삭제 
	@GetMapping("/delete/{talkCode}")
	public String delete(@PathVariable String talkCode, RedirectAttributes rttr) {
		if (talkservice.delete(talkCode)) {
			rttr.addFlashAttribute("result", "success");
			System.out.println(rttr);
			System.out.println(talkCode);
		}
		return "redirect:/talk/list";
	}
	
	
	
	
}
