package com.cook.talk.model.VO;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("qnAVO")
@Data
public class QnAVO {

	private String qnaCode;
	private String qnaTitle;
	private String qnaCont;
	private Date qnaDate;
	private String answer;
	private String userId;
}
