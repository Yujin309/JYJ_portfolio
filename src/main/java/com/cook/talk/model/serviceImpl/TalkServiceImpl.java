package com.cook.talk.model.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cook.talk.model.VO.TalkVO;
import com.cook.talk.model.dao.TalkDAO;
import com.cook.talk.model.service.TalkService;

@Service
public class TalkServiceImpl implements TalkService {
	
private static final String UploadDirectory=System.getProperty("user.dir")+ "\\src\\main\\resources\\static\\img\\";
	@Autowired
	TalkDAO talkDAO;

	public void insertTalk(TalkVO talkVO) {
		int talkNum = talkDAO.selectTalkCode() + 1;
		talkVO.setTalkCode("T-" + talkNum);
	}

//목록
	@Override
	public List<TalkVO> getTalkList() {
		return talkDAO.getTalkList();
	}


	
//수정 
	@Override
	public TalkVO update(TalkVO talkVO) {
		return talkDAO.update(talkVO.getTalkCont());

	}

	// 수정 완료
	@Override
	public void updateTalk(TalkVO talkVO) {
		talkDAO.updateTalk(talkVO);
	}

//삭제
	@Override
	public boolean delete(String talkCode) {
		return talkDAO.delete(talkCode) == 1;
	}

//게시판 code
	@Override
	public void insert(TalkVO talkVO) {
		int talkNum = talkDAO.selectTalkCode() + 1;
		talkVO.setTalkCode("T-" + talkNum);
		talkDAO.insert(talkVO);
	}

	// 클릭한 게시판
	@Override
	public TalkVO detail(String talkCode) {
		return talkDAO.detail(talkCode);
	}

	// 토크별 댓글
	@Override
	public TalkVO getBoardById(String talkCode) {
		return talkDAO.getBoardById(talkCode);
	}

	// 사진 업로드
	@Override
	public void upload(MultipartFile imgFile) {
		File file = new File(UploadDirectory);
		if (file.exists() == false) {
			file.mkdirs();
		}
		try {
			file = new File(UploadDirectory + imgFile.getOriginalFilename());
			imgFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int talkCount() {
int talkCount = talkDAO.talkCount();
		return talkCount();
	}



}
