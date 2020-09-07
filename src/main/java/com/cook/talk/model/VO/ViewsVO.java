package com.cook.talk.model.VO;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("viewsVO")
@Data
public class ViewsVO {

	private String rcpViewCode;
	private Date viewDate;
	private String rcpCode;
	private String userId;
}
