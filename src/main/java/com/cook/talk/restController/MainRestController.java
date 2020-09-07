package com.cook.talk.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cook.talk.model.dao.MainDAO;
import com.cook.talk.model.dto.ChefDTO;
import com.cook.talk.model.service.MainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
public class MainRestController {

	@Autowired
	MainDAO maindao;
	@Autowired
	MainService mainService;
	
	@PostMapping("/autoComplete")
	public String autoComplete(String searchWord) throws JsonProcessingException {
		System.out.println(searchWord);
		List<String> list=maindao.selectTitle(searchWord);
		ObjectMapper mapper=new ObjectMapper();
		
		JSONPObject json=new JSONPObject("JSON.parse", list);
		return mapper.writeValueAsString(list);
	}
	@GetMapping("/chef/follow")
	public String follow(String follow,String chefNick) {
		return mainService.followChef(follow, chefNick);
	}
	
}
