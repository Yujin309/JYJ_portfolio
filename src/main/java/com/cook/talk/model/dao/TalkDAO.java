package com.cook.talk.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cook.talk.model.VO.TalkVO;
import com.cook.talk.model.dto.RecipeDTO;

@Mapper
public interface TalkDAO {

	// 전체 목록
	public List<TalkVO> getTalkList();

	// 등록
	public int insert(TalkVO talkVO);

	// 삭제
	public int delete(String talkCode);

	// 수정 불러오기
	public TalkVO update(String talkCode);

	// 수정 버튼
	public int updateTalk(TalkVO talkVO);

	// 코드 증가
	public int selectTalkCode();

	// 보기
	public TalkVO detail(String talkCode);

	// 토크별 댓글 가져오기
	public TalkVO getBoardById(String talkCode);

	//이미지 업로드 
	public void upload(TalkVO talkVO);
	
	// 총갯수 카운팅
	public int talkCount();
		
	//페이징
	public List<TalkVO> getTalkListPaiging(int pageNum);
	
	

}
