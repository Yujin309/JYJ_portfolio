package com.cook.talk.model.VO;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("typeCatVO")
@Data
public class TypeCatVO {

	private String typeCode;
	private String cookType;
	private String cookSituation;
	private String cookMethod;
	

}
