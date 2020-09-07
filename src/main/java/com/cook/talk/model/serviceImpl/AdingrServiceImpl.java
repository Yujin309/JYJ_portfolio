package com.cook.talk.model.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cook.talk.model.VO.IngrVO;
import com.cook.talk.model.dao.AdIngrDAO;
import com.cook.talk.model.service.AdIngrService;


@Service
public class AdingrServiceImpl implements AdIngrService {

	@Autowired
	AdIngrDAO adingrDAO;

	@Override
	public String insertIngrImpl(IngrVO ingrVO, @RequestParam("ingrPic") MultipartFile file) {

		String UPLOADED_FOLDER = "D:/SecondFinal/FinalProj/src/main/resources/static/img/";

		int adingrNum = adingrDAO.selectIngrRcpCode() + 1;
		ingrVO.setRcpingrCode("M-" + adingrNum);

		System.out.println(file + "파일");
		System.out.println(ingrVO + "VO");

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}

		adingrDAO.insertIngr(ingrVO);
		return "success";
	};

}
