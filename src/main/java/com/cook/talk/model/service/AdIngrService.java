package com.cook.talk.model.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cook.talk.model.VO.IngrVO;

@Service
public interface AdIngrService {

	public String insertIngrImpl(IngrVO ingrVO, MultipartFile file);

}
