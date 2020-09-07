package com.cook.talk.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import com.cook.talk.model.VO.TalkComVO;
import com.cook.talk.model.VO.TalkVO;

public interface TalkService {
	// 목록 가져오기
	public List<TalkVO> getTalkList();

	// 추가
	public void insert(TalkVO talkVO);

	// 수정
	public TalkVO update(TalkVO talkVO);

	// 수정 가는거
	public void updateTalk(TalkVO talkVO);

	// 삭제
	public boolean delete(String talkCode);

	// 보기
	public TalkVO detail(String talkCode);

	// 토크 목록 가져오기
	public TalkVO getBoardById(String talkCode);

	// 이미지 업로드
	public void upload(MultipartFile file);

	//갯수
	public int talkCount();
	
}
