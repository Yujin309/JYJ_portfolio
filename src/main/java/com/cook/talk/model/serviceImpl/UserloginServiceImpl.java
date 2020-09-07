
package com.cook.talk.model.serviceImpl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.dao.UserDAO;
import com.cook.talk.model.dto.UserDTO;
import com.cook.talk.model.service.UserService;

@Service
public class UserloginServiceImpl implements UserService {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	private UserDTO userDTO;
	
	@Autowired
	UserDAO userDAO;

	//아이디 체크
	@Override
	public int userIdCheck(String userId) {
		return 0;
	}

	@Override
	public void joinUser(@Valid UserVO userVO) {

	}
	//닉네임 체크
	@Override
	public int nickNameCheck(String nickName) {
		return 0;
	}

	// 비밀번호 수정
	@Override
	public void updatePw(String userPw, String userId) {
		String pw = passwordEncoder.encode(userPw);
		userDAO.updatePw(pw, userId);
	}
	
	
	@Override
	public void updateNick(String nickName, String userId) {
		userDAO.updateNick(nickName, userId);
	}

}
