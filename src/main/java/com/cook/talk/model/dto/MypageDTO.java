package com.cook.talk.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MypageDTO {
	
	private String userId;
	private String nickName;
	private String userPic;
	
	private String rcpTitle;
	private String rcpPic;
	private int registerStatus;
	private String rcpCode;
	
	private String followChef;
	private int followC;
	private Date scrapDate;
	
	private String talkCode;
	private String talkCont;
	private Date talkDate;
	private String talkCom;
	private Date talkComDate;
	
	
	private String rcpCom;
	private Date rcpComDate;
	
}
