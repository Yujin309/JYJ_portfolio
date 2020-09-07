package com.cook.talk.model.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cook.talk.model.VO.IngrVO;
import com.cook.talk.model.VO.RcpIngrVO;
import com.cook.talk.model.VO.RcpOrderVO;
import com.cook.talk.model.VO.RecipeVO;
import com.cook.talk.model.VO.TagVO;
import com.cook.talk.model.VO.TypeCatVO;
import com.cook.talk.model.VO.ViewsVO;
import com.cook.talk.model.dao.RecipeDAO;
import com.cook.talk.model.dto.RecipeDTO;
import com.cook.talk.model.service.RecipeService;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired(required = false)
	private RecipeDAO recipeDAO;
	@Autowired(required = false)
	private IngrVO ingrVO;

	//재료 초성 분류
	public List<String> ingrNameList(int cs) {
		String[] chosungList = { "가", "나", "다", "라", "마", "바", "사", "아", "자", "차", "카", "타", "파", "하", "가" };

		List<String> ingrNameList = recipeDAO.getIngrName1(chosungList[cs], chosungList[cs + 1]);
		return ingrNameList;
	}

	@Override
	public String searched(IngrVO ingrVO) {
		if (ingrVO.getIngrName() != null) {
			ingrVO.getIngrName();
			return ingrVO.getIngrName();
		} else {
			return "일치하는 재료 없어요";
		}
	}

	@Override
	public List<RecipeDTO> getRcmmList(List<String> selectedIngr) {
		List<RecipeDTO> getRcmmList = recipeDAO.getRcmmList(selectedIngr);

		return getRcmmList;
	}

	@Override
	public List<String> getSearchedIngrName(String ingrName) {
		// TODO Auto-generated method stub
		return recipeDAO.getSearchedIngrName(ingrName);
	}
	
	

	
	//재료 상세페이지 조회
	@Override
	public List<IngrVO> getIngrDetail() {
		List<IngrVO> ingrDetail = recipeDAO.getIngrDetail();
		return ingrDetail;
	}
	
	
	//레시피 목록 조회
	@Override
	public List<RecipeDTO> getRecipeList() {
		List<RecipeDTO> recipeList = recipeDAO.getRecipeList();
		return recipeList;
	}
		
	
	//레시피 조회이력 저장
	public void insertRcpViews(ViewsVO viewsVO, RecipeVO recipeVO) {
		
		//1. rcpViewsCode 자동생성
		int rcpVnum = recipeDAO.selectRcpViewCode() +1;
		viewsVO.setRcpViewCode("RV-" +rcpVnum);
		System.out.println("voewsajfaliij"+viewsVO);
			
		//2. 레시피 상세 조회 시 viewDate는 mapper에서 NOW()로 insert한다
		
		//3. 해당 레시피의 rcpCode set한다
		viewsVO.setRcpCode(recipeVO.getRcpCode()); 	
		recipeDAO.insertRcpViews(viewsVO);	
		recipeDAO.empty();
		//4. userId는 세션처리
		
	}


	//레시피 갯수 카운팅
	@Override
	public int recipeCount() {
		int recipeCount = recipeDAO.recipeCount();
		return recipeCount();
	}

	
	//레시피 등록	
	@Override
	public void insertRecipeProc(boolean registerStatus, RecipeDTO recipeDTO, String userId){
		
		 RecipeVO recipeVO = recipeDTO.getRecipeVO();
		 TypeCatVO typeCatVO = recipeDTO.getTypeCatVO();
		 RcpIngrVO rcpIngrVO = recipeDTO.getRcpIngrVO();
		 RcpOrderVO rcpOrderVO = recipeDTO.getRcpOrderVO();
		 TagVO tagVO = recipeDTO.getTagVO();
		 
		// 1.typeCat 테이블 레코드 생성
		int tpcNum = recipeDAO.selectTypeCode() +1;
		 typeCatVO.setTypeCode("TC-" +tpcNum);
		
		recipeDAO.insertTypecatProc(typeCatVO);
		
		log.info(typeCatVO);
	
	
		//2. rcp 테이블 레코드 생성		
		int rcpNum = recipeDAO.selectRcpCode() +1;
		recipeVO.setRcpCode("R-" +rcpNum);
		
		//생성한 typeCode를 가져와서 rcp 테이블에 set한다
		recipeVO.setTypeCode(typeCatVO.getTypeCode()); 
		//registerStatus의 값을 set한다
		recipeVO.setRegisterStatus(registerStatus); 	
		
		recipeDAO.insertRcpProc(recipeVO);
		
		log.info(recipeVO);
		
		
		
		//3. rcpIngr 테이블 생성
				
		//rcpCode를 rcpIngr의 rcpCode에 set한다
		String [] strings2 = rcpIngrVO.getIngrCat().trim().split(",");
		String [] strings3 = rcpIngrVO.getUserIngr().trim().split(",");
		String [] strings4 = rcpIngrVO.getIngrWeigh().trim().split(",");
		int type = 0;
		for(int i = 0; i < strings3.length; i++) {
			int g = strings3.length/2;
			int cncNum = recipeDAO.selectConnectCode() +1;
			rcpIngrVO.setConnectCode("RM-" +cncNum);
			if(i==g) type +=1;
			rcpIngrVO.setIngrCat(strings2[type]);
		
			rcpIngrVO.setUserIngr(strings3[i]);
			rcpIngrVO.setIngrWeigh(strings4[i]);
			rcpIngrVO.setRcpCode(recipeVO.getRcpCode());		
			recipeDAO.insertRcpingrProc(rcpIngrVO);
		}
				
		log.info(rcpIngrVO);		
		
		//4. rcporder 테이블 생성
		//rcpCode를 rcpOrder의 rcpCode에 set한다
		String [] strings5 = rcpOrderVO.getCookOrder().trim().split(",");
		String [] strings6 = rcpOrderVO.getCookCont().trim().split(",");
		
		for(int i = 0; i < strings6.length; i++) {
			rcpOrderVO.setRcpCode(recipeVO.getRcpCode());		
			rcpOrderVO.setCookOrder(strings5[i]);
			rcpOrderVO.setCookCont(strings6[i]);
		
			recipeDAO.insertRcporderProc(rcpOrderVO);
		}
		
		log.info(rcpOrderVO);
		
		//5. tag 테이블 생성
		//해시태그 split로 잘라서 각 레시피 당 한개씩 DB에 저장
		String [] strings = tagVO.getTag().trim().split(",");
		for(int i = 0; i < strings.length; i++) {			
			tagVO.setRcpTagCode(recipeVO.getRcpCode()); //rcpCode를 rcpTagCode에 set한다
			tagVO.setTag(strings[i].trim()); //쉼표와 다음 태그 사이의 공백(trim)까지 포함
			recipeDAO.insertTagProc(tagVO);
			
			log.info(tagVO);
		
		
		}
	}

	//레시피 조회수 증가
	public int rcpViewsUpdate(String rcpCode) {
		return recipeDAO.rcpViewsUpdate(rcpCode);
		
	}

	
	//레시피 수정
	@Override
	public void updateRecipeProc(RecipeDTO recipeDTO) {
		
		 RecipeVO recipeVO = recipeDTO.getRecipeVO();
		 TypeCatVO typeCatVO = recipeDTO.getTypeCatVO();
		 RcpIngrVO rcpIngrVO = recipeDTO.getRcpIngrVO();
		 RcpOrderVO rcpOrderVO = recipeDTO.getRcpOrderVO();
		 TagVO tagVO = recipeDTO.getTagVO();
		 
		recipeDAO.updateRcp(recipeVO);
		recipeDAO.updateTypecat(typeCatVO);
		recipeDAO.updateRcpingr(rcpIngrVO);
		recipeDAO.updateRcporder(rcpOrderVO);
		recipeDAO.updateTag(tagVO);

		
		recipeDAO.updateRecipeProc(recipeDTO);
	}
		
		

	}




