package com.cook.talk.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cook.talk.model.VO.IngrVO;
import com.cook.talk.model.VO.RecipeVO;
import com.cook.talk.model.VO.TalkVO;
import com.cook.talk.model.VO.ViewsVO;
import com.cook.talk.model.dto.RecipeDTO;

@Service
public interface RecipeService {

	// 재료 이름 리스트
	public List<String> ingrNameList(int cs);

	// 재료 검색하기 in 냉파
	public String searched(IngrVO ingrVO);

	public List<String> getSearchedIngrName(String ingrName);

	public List<RecipeDTO> getRcmmList(List<String> selectedIngr);

	/* List<RecipeDTO> getRcmmList(IngrVO ingrVO); */
	
	
	
	//재료 상세페이지 조회
	public List<IngrVO> getIngrDetail();
	
	// 레시피 목록 조회
	public List<RecipeDTO> getRecipeList();	
	
	
	//레시피 조회이력 저장
	public void insertRcpViews(ViewsVO viewsVO, RecipeVO recipeVO);

	//레시피 총갯수
	public int recipeCount();
	
	// 레시피 글등록
	public void insertRecipeProc(boolean registerStatus, RecipeDTO recipeDTO, String userId);
	
	//레시피 조회수 증가
	public int rcpViewsUpdate(String rcpCode);
	
	//수정
	public void updateRecipeProc(RecipeDTO recipeDTO);



}
