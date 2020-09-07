
package com.cook.talk.model.service;

import javax.validation.Valid;

import com.cook.talk.model.VO.UserVO;

public interface UserService {
	
	//회원가입
	void joinUser(@Valid UserVO userVO);

	//아이디 중복체크 
	public int userIdCheck(String userId);

	//닉네임 중복체크
	public int nickNameCheck(String nickName);

	//비밀번호 업데이트 
	public void updatePw(String userPw, String userId);

	//닉네임 업데이트 
	public void updateNick(String nickName,String userId);

	
	
	/*
	 * static int getuserId(UserVO userVO) { // TODO Auto-generated method stub
	 * return 0; }
	 */


}