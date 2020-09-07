package com.cook.talk.model.VO;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("ingrVO")
@Data
public class IngrVO {

	private String rcpingrCode;
	private String ingrPic;
	private String ingrName;
	private String ingrSeason;
	private String ingrCalorie;
	private String ingrTemper;

}
