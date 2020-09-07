package com.cook.talk.model.VO;

import java.sql.Date;


//import lombok.AllArgsConstructor;

import lombok.Data;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Configuration;


@Alias("userVO")
@Configuration
@Data
public class UserVO { 
	
	private String userId;
	private String userPw;
	private String nickName;
	private Date birth;
	private int auth;
	private boolean gender;
	private int access;
	private String userPic;
	private int rcpUploads;
	private int followers;

	
}
