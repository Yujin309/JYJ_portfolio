package com.cook.talk.model.VO;

import java.sql.Date;

import lombok.Data;

@Data
public class TalkComVO {

	private String userPic;
	private String talkCode;
	private String talkComCode;
	private String talkCom;
	private Date talkComDate;
	private String userId;
	private String nickName;

}
