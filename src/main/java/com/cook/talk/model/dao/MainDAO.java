package com.cook.talk.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cook.talk.model.VO.RecipeVO;
import com.cook.talk.model.dto.ChartDTO;
import com.cook.talk.model.dto.ChefDTO;
import com.cook.talk.model.dto.IndexDTO;
import com.cook.talk.model.dto.MainDTO;
import com.cook.talk.model.dto.RcpSDTO;

@Mapper
public interface MainDAO {

	public List<IndexDTO> recipeList(List<String> repCode);
	public List<IndexDTO> newRecipeList();
	public MainDTO totalSelect();
	public List<RcpSDTO> recipeSearch(String searchWord,String status,int page);
	public List<String> selectTitle(String searchWord);
	public List<ChefDTO> selectChef(String chefNick);
	public int countRecipe(String searchWord);
	public List<ChartDTO> selectGender(String rcpCode);
	public List<ChartDTO> selectBirth(String rcpCode);
	public List<ChartDTO> selectMonth(String rcpCode);
	
	public void followChef(String userId,String chefNick);
	public void unfollowChef(String userId,String chefNick);
	public String userRecommend(String userId);
	public String selectUserPic(String userId);
}
