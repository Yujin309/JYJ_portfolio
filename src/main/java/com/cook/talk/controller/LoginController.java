package com.cook.talk.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.dao.MainDAO;
import com.cook.talk.model.dao.UserDAO;
import com.cook.talk.model.dto.UserDTO;
import com.cook.talk.model.naver.NaverLoginBO;
import com.cook.talk.model.service.EncryptionService;
import com.cook.talk.model.service.UserService;
import com.cook.talk.model.serviceImpl.UserServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Controller
@Slf4j
public class LoginController {

	@Autowired
	private UserVO userVO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private EncryptionService encryption;
	@Autowired
	private MainDAO maindao;
	@Autowired
	private UserService service;

	// 로그인 기능
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest req, HttpSession session) {
		String naverAuthUrl = NaverLoginBO.getAuthorizationUrl(session);
		// 네이버 아이디로 인증 url를 생성하기 위해 메소드 호출
		// 생성한 인증 url를 view로 전달
		model.addAttribute("naver_url", naverAuthUrl);
		model.addAttribute("message", req.getServletContext());
		System.out.println(naverAuthUrl);
		return "/login/login";
	}

	@RequestMapping("goJoin")
	public String goJoin() {
		return "join";
	}

	@RequestMapping(value = "/userUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public String userUpdate(String nickName, Principal principal) {
		service.updateNick(nickName, principal.getName());
		return "/login/userUpdate";
	}

	@PostMapping("/login/userDelete")
	public String userDelete() {
		return "/login/login";
	}

	// 로그아웃

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) throws IOException {
		System.out.println("여기는 logout");
		session.invalidate();
		return "redirect:index";
	}

	@GetMapping("/admin")
	public String adminPage(@AuthenticationPrincipal User user, Map<String, Object> model) {
		model.put("currentAdminId", user.getUsername());
		return "adminpage";
	}

	@RequestMapping(value = "/gologin", method = RequestMethod.GET)
	public String gologin(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		System.out.println("aslihifcuoasshfvoih");
		// re가 null이 아니거나 refer가 null 이 아닌 경우
		if (request.getRequestURL() != null && request.getHeader("referer") != null) {
			// 이전 페이지가 로그인겟 이거나 직접 로그인 url에 접속하지 않았을 경우 referer 저장
			if (!(request.getRequestURL().equals("/login/gologin")
					&& request.getHeader("referer").equals("http://localhost/login/gologin"))) {
				// dest세션에 이전 페이지 정보 저장
				session.setAttribute("dest", request.getHeader("referer"));
			}
		}
		return "/user/login";
	}

	private UserServiceImpl userServiceImpl;
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(UserVO userVO) {
		return "join/join";
	}// 화면보여주는 것.

	@PostMapping("/join")
	public String execJoin(UserVO userVO, Errors errors, Model model) {
		userServiceImpl.joinUser(userVO);
		System.out.println(userVO.getUserId());
		encryption.encryption(userVO.getUserId());
		return "redirect:/index";

	}// 회원가입 기능

	@GetMapping("/updatePw")
	public String updatePw(Principal principal) {
		log.info(principal.getName());
		return "mypage/userModify";

	}

	// 비밀번호 업데이트
	@PostMapping("/updatePwUpdate")
	public String updatePw(@RequestParam String userPw, Principal principal) {
		System.out.println(userPw);
		service.updatePw(userPw, principal.getName());
		return "mypage/userModify";

	}

	/*
	 * @PostMapping("/join2") public String execJoin(@Valid UserDTO userDTO, Errors
	 * errors, Model model) { // @Valid 입력데이터가 dto클래스로 캡슐화 되어 넘어올때 유효성을 체크하라는
	 * 어노테이션이다 /dto의 어노테니션 기준으로 체크 if (errors.hasErrors()) { // 회원가입 실패시, 입력 데이터를 유지
	 * model.addAttribute("userDTO", userDTO); // errors.hasErrors() // 유효성 통과 못한
	 * 필드와 메세지 핸들링 유효성 검사에 실패한 필드가 있는지 확인. UserVO userVO = new UserVO();
	 * userVO.setUserId(userDTO.getUserId()); userVO.setUserPw(userDTO.getUserPw());
	 * userServiceImpl.joinUser(userVO); return "redirect:/login/login"; }
	 * 
	 * Map<String, String> validatorResult =
	 * UserServiceImpl.validateHandling(errors); for (String key :
	 * validatorResult.keySet()) { model.addAttribute(key,
	 * validatorResult.get(key)); }
	 * 
	 * return "/join/join";
	 * 
	 * }
	 */

	@GetMapping("/loginConfirm/{accessCode}")
	public String loginConfirm(@PathVariable String accessCode, Model model) {
		model.addAttribute("total", maindao.totalSelect());
		System.out.println(accessCode);
		encryption.Decrypt(accessCode);
		return "redirect:/index";
	}
}