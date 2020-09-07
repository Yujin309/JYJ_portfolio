package com.cook.talk.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("chartDTO")
@Data
public class ChartDTO {

	private int count;
	private String axis;
}
