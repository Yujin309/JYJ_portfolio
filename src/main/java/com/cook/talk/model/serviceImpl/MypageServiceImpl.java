package com.cook.talk.model.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cook.talk.model.VO.QnAVO;
import com.cook.talk.model.VO.TalkComVO;
import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.dao.MypageDAO;
import com.cook.talk.model.dao.QnADAO;
import com.cook.talk.model.dto.MypageDTO;
import com.cook.talk.model.service.MypageService;

@Service
public class MypageServiceImpl implements MypageService{

	private static final String String = null;

	@Autowired(required = false)
	private MypageDAO mypageDAO;
	
	@Autowired(required = false)
	private QnADAO qnADAO;
	
	@Override
	public String user(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//작성중인 레시피 가져오기
	@Override
	public List<MypageDTO> getMyRecipeIng(String userId) {
		List<MypageDTO> getmyRecipeIng = mypageDAO.getMyRecipeIng(userId);
		return getmyRecipeIng;
	}

	
	//작성완료된 레시피 가져오기
	@Override
	public List<MypageDTO> getMyRecipeWritten(String userId) {
		List<MypageDTO> getMyRecipeWritten = mypageDAO.getMyRecipeWritten(userId);
		return getMyRecipeWritten;
	}

	
	//팔로우한 쉐프 목록 가져오기
	@Override
	public List<MypageDTO> getMyFollow(String userId) {
		List<MypageDTO> getMyFollow = mypageDAO.getMyFollow(userId);
		return getMyFollow;
	}

	
	//스크랩한 레시피 목록 가져오기
	@Override
	public List<MypageDTO> getMyScrapedRecipe(String userId) {
		List<MypageDTO> getMyScrapedRecipe = mypageDAO.getMyScrapedRecipe(userId);
		return getMyScrapedRecipe;
	}

	
	//내가 작성한 토크 목록 가져오기
	@Override
	public List<MypageDTO> getMyTalk(String userId) {
		List<MypageDTO> getMyTalk = mypageDAO.getMyTalk(userId);
		return getMyTalk;
	}
	
	
	//내가 작성한 모든 댓글(RCP+TK) 가져오기
	@Override
	public List<MypageDTO> getAllMyCom(String userId) {
		List<MypageDTO> getAllMyCom = mypageDAO.getAllMyCom(userId);
		return getAllMyCom;
	}
	
	//내가 작성한 토크 댓글만 가져오기
	@Override
	public List<TalkComVO> getMyTalkCom(String userId) {
		List<TalkComVO> getMyTalkCom = mypageDAO.getMyTalkCom(userId);
		return getMyTalkCom;
	}
	
	//내가 작성한 레시피 댓글만 가져오기
	@Override
	public List<MypageDTO> getMyRcpCom(String userId) {
		List<MypageDTO> getMyRcpCom = mypageDAO.getMyRcpCom(userId);
		return getMyRcpCom;
	}
	
	//모든 댓글(RCP+TK) 검색하기
	@Override
	public List<MypageDTO> getSearchAllMyCom(String talkCom, String userId) {
		List<MypageDTO> getSearchAllMyCom = mypageDAO.getSearchAllMyCom(talkCom, userId);
		return getSearchAllMyCom;
	}


	@Override
	public void rqMyInq(QnAVO qnAVO) {
		int qnACode = mypageDAO.selectQnACode() + 1;
		qnAVO.setQnaCode("Q-"+qnACode);
		mypageDAO.rqMyInq(qnAVO);
	}

	@Override
	public List<QnAVO> getMyInq(String userId) {
		List<QnAVO> getMyInq = mypageDAO.getmyInq(userId); 
		return getMyInq;
	}

	//팔로우한 쉐프 검색하기
	@Override
	public List<MypageDTO> getSearchMyFollow(String followChef, String userId) {
		return mypageDAO.getSearchMyFollow(followChef, userId);
	}

	@Override
	public List<MypageDTO> getSearchMyTalk(String talkCont, String userId) {
		return mypageDAO.getSearchMyTalk(talkCont, userId);
		
	}

	@Override
	public List<MypageDTO> getSearchMyScraped(String rcpTitle, String userId) {
		return mypageDAO.getSearchMyScraped(rcpTitle, userId);
	}

	@Override
	public List<MypageDTO> getSearchTalkCom(String talkCom, String userId) {
		return mypageDAO.getSearchTalkCom(talkCom, userId);
	}

	@Override
	public List<MypageDTO> getSearchRcpCom(String rcpCom, String userId) {
			return mypageDAO.getSearchRcpCom(rcpCom, userId);
	}

	@Transactional
	public void deleteRcp(String rcpCode, String userId) {
		mypageDAO.deleteRcp(rcpCode, userId);
	}


	/*
	 * @Override public List<MypageDTO> getMyPic(String userId) { List<MypageDTO>
	 * getMyPic = mypageDAO.getMyPic(userId); return getMyPic; }
	 */

}
