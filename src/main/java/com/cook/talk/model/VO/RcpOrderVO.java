package com.cook.talk.model.VO;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("rcpOrderVO")
@Data
public class RcpOrderVO {

	private String rcpCode;
	private String cookOrder;
	private String cookCont;
	private String cookPic;
}
