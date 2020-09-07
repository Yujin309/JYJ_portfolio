package com.cook.talk.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("rcpSDTO")
public class RcpSDTO {

	private String rcpPic;
	private String rcpTitle;
	private String nickName;
	private String rcpCode;
}
