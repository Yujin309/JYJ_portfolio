package com.cook.talk.ck;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.dao.TalkDAO;
import com.cook.talk.model.dao.UserDAO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class HYujin {
	@Autowired
	UserDAO userDao;
	@Autowired
	TalkDAO talkDAO;
	@Autowired
	BCryptPasswordEncoder hhaa;
	@Test
	/*
	 * public void find() {
	 * log.info(userDao.findUserById("hahah@naver.com").toString()); }
	 */
	public void find() {
		log.info(userDao.findUserById("hahah@naver.com").toString());
	}

	public void save() throws Exception {


		UserVO user = new UserVO();
		user.setUserId("grand@naver.com");
		user.setUserPw(hhaa.encode("0000"));
		user.setNickName("하나하나");
		user.setBirth(new Date(1994 - 06 - 05));
		user.setGender(true);
		user.setAccess(1);
		user.setAuth(0);

		userDao.join(user);

	}
	
	@Test
	public void list() {
		talkDAO.getTalkList().forEach(talk -> log.info(talk+""));
	}
	
	@Autowired
	private MockMvc mvc;
	@Test
	public void test( )throws Exception{
		mvc.perform((MockMvcRequestBuilders.get("/list"))).andDo(print());
	}
	
	
	
	
}

		