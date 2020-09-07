package com.cook.talk.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cook.talk.model.VO.TalkComVO;
import com.cook.talk.model.VO.TalkVO;

@Mapper
public interface TalkComDAO {

	// 전체 목록
	public List<TalkVO> list (String talkComCode);

	// 등록
	public void createCom(TalkComVO talkComVO);

	// 삭제
	public void deleteCom(String talkComCode);
	
	//수정 버튼 
	public int updateCom(@Param("talkComCode") String talkComCode, @Param("talkCom") String talkCom);
	//public int upda2teTalk(TalkVO talkVO);

	//코드 증가
	public int selectTalkComCode();
	
	//개수
	public int comCount();

	

}
