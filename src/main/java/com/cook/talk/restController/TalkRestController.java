package com.cook.talk.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cook.talk.model.VO.TalkComVO;
import com.cook.talk.model.VO.TalkVO;
import com.cook.talk.model.dao.TalkDAO;
import com.cook.talk.model.service.TalkComService;
import com.cook.talk.model.service.TalkService;
import com.cook.talk.util.FileTrancefer;

import groovy.util.logging.Log4j;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Log4j
public class TalkRestController {
	private TalkService service;

	@Autowired
	TalkDAO talkDAO;

	@PostMapping("com/upload")
	public void imgload(MultipartFile multipartFile) {
		service.upload(multipartFile);
	}

	// 이미지 업로드
	@PostMapping("talk/upload")
	public void talkImg(@RequestParam("talkPic") MultipartFile multipartfile, TalkVO talkVO) {
		String talkPicture = FileTrancefer.requestFileTrancefer(multipartfile, "talkPic/");
		talkVO.setTalkVideo(talkPicture);
		talkDAO.insert(talkVO);
	}


}
