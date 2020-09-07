package com.cook.talk.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cook.talk.model.VO.IngrVO;

@Mapper
public interface AdIngrDAO {

	public List<IngrVO> searchIngr(String ingrVO);

	public List<IngrVO> allSelectIngr();

	public void insertIngr(IngrVO ingrVO);

	public void deleteIngr(List<String> rcpingrCode);

	public int selectIngrRcpCode();

	// 재료 파트 페이지 네이션
	public int countPaginationIngr();

	public List<IngrVO> countPaginationIngrLimit(int pageNUM);

}
