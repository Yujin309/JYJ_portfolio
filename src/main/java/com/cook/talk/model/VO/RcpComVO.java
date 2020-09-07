package com.cook.talk.model.VO;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("rcpComVO")
@Data
public class RcpComVO {

	private String rcpComCode;
	private String rcpCom;
	private Date rcpComDate;
	private String rcpCode;
	private String userId;
}
