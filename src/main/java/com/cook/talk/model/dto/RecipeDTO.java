package com.cook.talk.model.dto;

import org.springframework.beans.factory.annotation.Autowired;


import com.cook.talk.model.VO.RcpIngrVO;
import com.cook.talk.model.VO.RcpOrderVO;
import com.cook.talk.model.VO.RecipeVO;
import com.cook.talk.model.VO.TagVO;
import com.cook.talk.model.VO.TypeCatVO;
import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.VO.RcpComVO;

import lombok.Data;

@Data
public class RecipeDTO {

	private String rcpCode;
	
	

	private String userId;
	
	@Autowired(required = false)
	private RecipeVO recipeVO;
	
	@Autowired(required = false)
	private UserVO userVO;
	
	@Autowired(required = false)
	private TypeCatVO typeCatVO;
	
	@Autowired(required = false)
	private RcpIngrVO rcpIngrVO;
	
	@Autowired(required = false)
	private RcpOrderVO rcpOrderVO;
	
	@Autowired(required = false)
	private TagVO tagVO;

	@Autowired(required = false)
	private RcpComVO rcpComVO;

}
