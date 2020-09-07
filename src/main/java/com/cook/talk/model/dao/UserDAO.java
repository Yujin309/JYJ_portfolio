package com.cook.talk.model.dao;

import java.util.Optional;

//Simport javax.xml.ws.ServiceMode;

import org.apache.ibatis.annotations.Mapper;

import com.cook.talk.model.VO.UserVO;

@Mapper
//@ServiceMode

public interface UserDAO {
	
	//아이디 중복체크
	public int userIdCheck(String userId);
	
	// 닉네임 중복 체크
	public int nickNameCheck(String nickName);
	
	//비밀 번호 업데이트 
	public int updatePw(String userPw, String userId);
	
	//닉네임 바꾸기 
	public int updateNick(String nickName,String userId);

	//해당 아이디 찾기 
	public UserVO findUserById(String id);

	//회원가입
	public void join(UserVO user) throws Exception;

	//로그인
	public int login(UserVO userVO);

	
	public Optional<UserVO> findByEmail(String userEmail);

	
	public static void setPassword(String encode) {
	}

	public static int getuserId(UserVO userVO) {
		return 0;
	}

	public void updateAccess(String userId);

}