package com.cook.talk.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("indexDTO")
public class IndexDTO {

	private String nickName;
	private String rcpTitle;
	private String rcpPic;
}
