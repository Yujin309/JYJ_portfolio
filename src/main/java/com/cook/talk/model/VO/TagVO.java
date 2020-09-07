package com.cook.talk.model.VO;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("tagVO")
@Data
public class TagVO {

	private String rcpTagCode;
	private String tag;
}
