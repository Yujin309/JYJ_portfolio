package com.cook.talk.model.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cook.talk.model.VO.TalkComVO;
import com.cook.talk.model.dao.TalkComDAO;
import com.cook.talk.model.service.TalkComService;
import lombok.Setter;

@Service
public class TalkComServiceImpl implements TalkComService {
	@Autowired
	TalkComDAO comDAO;

	@Setter(onMethod_ = @Autowired)
	private TalkComService talkComService;

	// 작성
	@Override
	public void createCom(TalkComVO comVO) {
		int talkNum = comDAO.selectTalkComCode() + 1;
		System.err.println(talkNum);
		comVO.setTalkComCode("TC-" + talkNum);
		comDAO.createCom(comVO);
	}

	// 수정
	@Override
	public void updateCom(String talkComCode, String talkCom) {
		comDAO.updateCom(talkComCode, talkCom);
	}
	

	// 삭제
	@Override
	public void deleteCom(String talkComCode) {
		comDAO.deleteCom(talkComCode);
	}

	// 목록

	@Override
	public int comCount() {
	int comCount=comDAO.comCount();
		return comCount();
	}

	@Override
	public List<TalkComVO> searchComment(String talkCode) {
		return talkComService.searchComment(talkCode);
	}

	
}
