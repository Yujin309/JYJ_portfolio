package com.cook.talk.security;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Join {
	@Autowired
	SqlSession sqlSession;

	public int idCheck(String userId) {

		System.out.println("디비에서 id체크 ");
		int result = sqlSession.selectOne("userMapper.idCheck", userId);
		return result;
	}// 아이디 체크

}
