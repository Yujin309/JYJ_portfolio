package com.cook.talk.model.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cook.talk.model.dao.MainDAO;
import com.cook.talk.model.dto.IndexDTO;
import com.cook.talk.model.service.MainService;

@Service
public class MainServiceImpl implements MainService {

	@Autowired(required = false)
	private MainDAO main;

	@Override
	public List<IndexDTO> recipeList(String expl) {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		// parameter 세팅
		// REST API 호출
		String result = restTemplate.postForObject("http://localhost:5000/recommend",main.userRecommend(expl), String.class);
		result = result.substring(2, result.length());
		result = result.substring(0, result.length() - 3);
		String[] re = result.split("\",\"");
		List<String> rcpCodeList=new ArrayList<String>();
		for (String k:re) {
			rcpCodeList.add(k);
		}
		
		return main.recipeList(rcpCodeList);
	}

	@Override
	public String followChef(String follow,String chefNick) {
		String userId="zleda9@naver.com";
		String result;
		System.out.println(follow);
			if(follow.equals("Follow")||follow.equals("FollowFollow")) {
				main.followChef(userId, chefNick);
				result="Follow완료";
			}else {
				main.unfollowChef( userId, chefNick);
				result="Unfollow완료";
			}
		return result;
	}

}
