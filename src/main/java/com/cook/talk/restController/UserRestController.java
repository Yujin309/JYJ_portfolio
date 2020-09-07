package com.cook.talk.restController;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cook.talk.model.dao.UserDAO;
import com.cook.talk.model.service.UserService;

@RestController

public class UserRestController {

	@Autowired
	UserDAO userDAO;
	@Autowired
	UserService userServce;

	//아이디 체크 
	@RequestMapping("/idCheck.do")
	@ResponseBody
	public int idCheck(@RequestBody String userId) {
		int count = 0;
		count = userDAO.userIdCheck(userId);
		System.out.println(userId);
		return count;

	}
//닉네임체크
	@PostMapping("/nickNameCheck")
	@ResponseBody
	public int nickNameCheck(@RequestBody String nickName) {
		int Ncount = 0;
		Ncount = userDAO.nickNameCheck(nickName);
		System.out.println(nickName);
		return Ncount;
	}

}