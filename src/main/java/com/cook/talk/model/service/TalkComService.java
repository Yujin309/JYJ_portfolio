package com.cook.talk.model.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cook.talk.model.VO.TalkComVO;

public interface TalkComService {

	// 목록
	// public List<TalkVO> searchComment(@Param("talkComCode") String talkComCode);

	// 입력
	public void createCom(TalkComVO comVO);

	// 수정
	public void updateCom(String talkComCode, String talkCom);

	// 삭제
	public void deleteCom(String talkComCode);

	// 개수
	public int comCount();

	
	public List<TalkComVO> searchComment(@Param("talkComCode") String talkCode);

}
