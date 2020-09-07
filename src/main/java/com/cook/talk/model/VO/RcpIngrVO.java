package com.cook.talk.model.VO;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("rcpIngrVO")
@Data
public class RcpIngrVO {

	private String connectCode;
	private String ingrCat;
	private String userIngr;
	private String ingrName;
	private String ingrWeigh;
	private String rcpingrCode;
	private String rcpCode;
	
}
