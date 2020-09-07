package com.cook.talk.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cook.talk.model.VO.IngrVO;
import com.cook.talk.model.VO.RcpIngrVO;
import com.cook.talk.model.VO.RcpOrderVO;
import com.cook.talk.model.VO.RecipeVO;
import com.cook.talk.model.VO.TagVO;
import com.cook.talk.model.VO.TalkVO;
import com.cook.talk.model.VO.TypeCatVO;
import com.cook.talk.model.VO.ViewsVO;
import com.cook.talk.model.dto.RecipeDTO;

@Service
@Mapper
public interface RecipeDAO {

	//Call ingrNameList
	public List<IngrVO> allSelectIngrList(IngrVO ingrVO);
	
	//Searching IngrName?
	public List<String> getIngrName(String chosung1, String chosung2);
	
	//Call searched ingrName
	public List<String> getSearchedIngrName(String ingrName);
	
	public List<String> getIngrName1(String chosung1, String chosung2);
	
	public List<RecipeDTO> getRcmmList(List<String> selectedIngr);
	
	public void empty();
	
	
	//레시피 재료 상세페이지 조회
	public List<IngrVO> getIngrDetail();
	
	//레시피 목록 조회+페이징
	public List<RecipeDTO> getRecipeList();
	
	public List<RecipeDTO> getRcpListPaiging(int pageNum);

	//레시피 랭킹 조회
	public List<RecipeDTO> getRankD();
	public List<RecipeDTO> getRankW();
	public List<RecipeDTO> getRankM();
	
	//rcpViewsCode 자동생성
	public int selectRcpViewCode();	
	
	//레시피 조회이력 저장
	public void insertRcpViews(ViewsVO viewsVO);
	
	//레시피 총갯수 카운팅
	public int recipeCount();
			
	//레시피 글보기
	public RecipeDTO selectRcptpView(String rcpCode);
	public List<RcpIngrVO> selectRcpIngrView(String rcpCode);
	public List<RcpOrderVO> selectRcpOrderView(String rcpCode);
	public List<TagVO> SelectTagView(String rcpCode); //List로 가져오기
		
	
	//레시피 등록proc
	//rcpCode 자동생성
	public int selectRcpCode();
	//typeCode 자동생성
	public int selectTypeCode();
	//rcpingr.connectcode 자동생성
	public int selectConnectCode();

	//레시피 등록
	public void insertRecipeProc(MultipartFile file, RecipeDTO recipeDTO, String userId);
	void insertRcpProc(RecipeVO recipeVO);
	void insertTypecatProc(TypeCatVO typeCatVO);
	void insertRcpingrProc(RcpIngrVO rcpIngrVO);
	void insertRcporderProc(RcpOrderVO rcpOrderVO);
	void insertTagProc(TagVO tagVO);

	//레시피 조회수 증가
	public int rcpViewsUpdate(String rcpCode);

			
	//레시피 수정
	public void updateRecipeProc(RecipeDTO recipeDTO);
	void updateRcp(RecipeVO recipeVO);
	void updateTypecat(TypeCatVO typeCatVO);
	void updateRcpingr(RcpIngrVO rcpIngrVO);
	void updateRcporder(RcpOrderVO rcpOrderVO);
	void updateTag(TagVO tagVO);
	
	
	
}

