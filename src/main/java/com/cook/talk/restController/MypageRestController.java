package com.cook.talk.restController;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.dao.MypageDAO;
import com.cook.talk.model.dao.UserDAO;
import com.cook.talk.model.dto.MypageDTO;
import com.cook.talk.model.service.MypageService;

@RestController
public class MypageRestController {

	@Autowired
	UserDAO userDAO;
	
	@Autowired(required = false)
	MypageDAO mypageDAO;
	
	@Autowired(required = false)
	MypageService mypageService;
	

	
	@PostMapping("/searchMyFollow")
	public List<MypageDTO> getSearchMyFollow(String followChef, MypageDTO mypageDTO, Principal principal){
		
		mypageDTO.setUserId(principal.getName());
		
		System.out.println(followChef);
		System.out.println(mypageService.getSearchMyFollow(followChef, principal.getName()));

		return mypageService.getSearchMyFollow(followChef, principal.getName());

	}
	
	@PostMapping("/searchMyTalk")
	public List<MypageDTO> getSearchMyTalk(String talkCont, MypageDTO mypageDTO, Principal principal) {
		mypageDTO.setUserId(principal.getName());
		
		System.out.println(talkCont);
		List<MypageDTO> mypageDTOs=  mypageService.getSearchMyTalk(talkCont, principal.getName());
		/* Date date = mypageDTOs.get(0).getTalkDate(); */
		
		return mypageService.getSearchMyTalk(talkCont, principal.getName());
	}
	
	
	
	@PostMapping("/searchMyScrap")
	public List<MypageDTO> getSearchMyScraped(String rcpTitle, MypageDTO mypageDTO, Principal principal){
		mypageDTO.setUserId(principal.getName());
		
		System.out.println(rcpTitle);
		System.out.println(mypageService.getSearchMyScraped(rcpTitle, principal.getName()));
		return mypageService.getSearchMyScraped(rcpTitle, principal.getName());
	}
	
	
	
	
	
	@PostMapping("/searchMyAllCom")
	public List<MypageDTO> getSearchAllMyCom(String talkCom, MypageDTO mypageDTO, Principal principal){
		mypageDTO.setUserId(principal.getName());
		
		System.out.println("searchMyAllCom==="+talkCom);
		System.out.println(mypageService.getSearchAllMyCom(talkCom, principal.getName()));
		return mypageService.getSearchAllMyCom(talkCom, principal.getName());
	}
	
	
	
	
	@PostMapping("/searchMyTalkCom")
	public List<MypageDTO> getSearchTalkCom(String talkCom, MypageDTO mypageDTO, Principal principal){
		mypageDTO.setUserId(principal.getName());
		
		System.out.println("=====searchMyTalkCom======"+talkCom);
		List<MypageDTO> mypageDTOs= mypageService.getSearchTalkCom(talkCom, principal.getName());
		for(MypageDTO dto:mypageDTOs)
			System.out.println("mypageDTOs===>"+dto);
		return mypageDTOs;
	}
	
	
	
	
	
	
	@PostMapping("/searchMyRcpCom")
	public List<MypageDTO> getSearchRcpCom(String rcpCom, MypageDTO mypageDTO, Principal principal) {
		mypageDTO.setUserId(principal.getName());
		
		System.out.println("searchMyRcpCom"+rcpCom);
		System.out.println(mypageService.getSearchTalkCom(rcpCom, principal.getName()));
		return mypageService.getSearchRcpCom(rcpCom, principal.getName());
	}
	 
	
	
	
	
	//닉네임체크
		@PostMapping("/mypage/nickNameCheck")
		@ResponseBody
		public int nickNameCheck(@RequestBody String nickName) {
			int Ncount = 0;
			Ncount = userDAO.nickNameCheck(nickName);
			System.out.println(nickName);
			return Ncount;
		}
	
	
	
	/*
	 * @PostMapping("/mypage/ans/{qnaCode}") public String
	 * showAns(@RequestParam("qnaCode") String qnaCode, RedirectAttributes rttr) {
	 * 
	 * return "redirect:/mypage/myInquiryList"; }
	 */
	
	
}
