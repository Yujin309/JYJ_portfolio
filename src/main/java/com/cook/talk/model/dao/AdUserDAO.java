package com.cook.talk.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.dto.UserDTO;

@Mapper
public interface AdUserDAO {
	public List<UserDTO> allSelectUserId();

	public void deleteUserID(UserVO userId);

	/* public boolean deleteUserID(); */

	public void updateUserNickName(UserVO userVO);

	public List<UserVO> searchUser(String nickName);

	public int countPaginationUser();
	 
	public List<UserVO> countPaginationUserLimit(int pageNum);
	
}
