package com.cook.talk.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cook.talk.model.dao.ChefDAO;

@SpringBootTest
public class Test {

	@Autowired(required = false)
	private ChefDAO chef;
	
	@org.junit.jupiter.api.Test
	public void dao() {
		chef.allSelectChef("rcpviews");
	}

}
