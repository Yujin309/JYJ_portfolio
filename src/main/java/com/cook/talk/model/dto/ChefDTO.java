package com.cook.talk.model.dto;


import org.apache.ibatis.type.Alias;

import com.cook.talk.model.VO.RecipeVO;
import com.cook.talk.model.VO.UserVO;

import lombok.Data;


@Alias("chefDTO")
@Data
public class ChefDTO  {


	private int rcpViews;
	private UserVO userVO;
	
}
