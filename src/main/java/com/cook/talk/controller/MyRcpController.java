package com.cook.talk.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.dao.MypageDAO;
import com.cook.talk.model.dto.MypageDTO;
import com.cook.talk.model.service.MypageService;
import com.cook.talk.model.service.UserService;
import com.cook.talk.util.FileTrancefer;

@Controller
public class MyRcpController {

	@Autowired
	MypageDAO mypageDAO;
	
	@Autowired
	MypageService mypageService;
	
	@Autowired
	private UserService service;
	
	//마이페이지-사용자 개인 사진 수정페이지로 가기
	@GetMapping("/mypage/userPic")
	public String userPic() {
		
		return "/mypage/myPic";
	}
	
	//마이페이지-레시피-나의 작성중인 레시피
	@GetMapping("/mypage/myRecipeIng")
	public String myRecipeIng(Model model, MypageDTO mypageDTO, Principal principal, HttpSession session) {
		mypageDTO.setUserId(principal.getName());
		List<MypageDTO> lists = mypageDAO.getMyRecipeIng(principal.getName());
		
		model.addAttribute("myRecipeIng", lists);
		
		session.setAttribute("userPic", lists.get(0).getUserPic());
		session.setAttribute("nickName", lists.get(0).getNickName());
		System.err.println(lists);
		return "/mypage/myRecipeIng";
	}
	
	//마이페이지-레시피-나의 작성완료된 레시피
	@GetMapping("/mypage/myWritten")
	public String myWritten(Model model, MypageDTO mypageDTO, Principal principal, String userPic) {
		mypageDTO.setUserId(principal.getName());
		
		List<MypageDTO> lists = mypageDAO.getMyRecipeWritten(principal.getName());
		/* List<MypageDTO> userPics = mypageDAO.getMyPic(principal.getName()); */
		
		model.addAttribute("myWritten", lists);
		/* model.addAttribute(userPic, userPics); */
		System.err.println(lists);
		return "/mypage/myWritten";
	}
	
	
	  @GetMapping("/deleteRcp/{rcpCode}") 
	  public String deleteRcp(@PathVariable("rcpCode") String rcpCode, MypageDTO mypageDTO, Principal principal) {
		  mypageDTO.setUserId(principal.getName());
			mypageService.deleteRcp(rcpCode, principal.getName());
			System.out.println("dao 끝 ");
	  
	  return "redirect:/mypage/myWritten"; 
	  
	  }
	  
	  
		@PostMapping("/mypage/modifyUserPic")
		public String modifyUserPic(UserVO userVO, Model model, @RequestParam String userNickName, @RequestParam String userPw3,
				@RequestParam("file") MultipartFile multipartfile, Principal principal) {
			
			userVO.setUserId(principal.getName());
			
			String userPic = FileTrancefer.requestFileTrancefer(multipartfile, "userPic/");
			userVO.setUserPic(userPic);
			System.out.println("추가된 파일명:" + multipartfile);
			
			mypageDAO.modifyUserPic(userPic, principal.getName());
			service.updateNick(userNickName, principal.getName());
			service.updatePw(userPw3, principal.getName());
			
			return "redirect:/mypage/myRecipeIng";
		}
	 
	
}
