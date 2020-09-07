package com.cook.talk.model.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cook.talk.model.dto.IndexDTO;

public interface MainService {

	public List<IndexDTO> recipeList(String expl);
	public String followChef(String follow,String chefNick);
	
}
