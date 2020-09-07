package com.cook.talk.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;

import com.cook.talk.model.VO.QnAVO;
@Mapper
@Alias("qnADAO")
public interface QnADAO {
	public void insertReply(QnAVO qna);
	public List<QnAVO> selectQna();
	
	/* public void rqMyInq(String qnaTitle, String qnaCont); */
	
}
