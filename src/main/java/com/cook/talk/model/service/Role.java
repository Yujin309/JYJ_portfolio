package com.cook.talk.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role{
	MEMBER("Role_MEMBER"),
	ADMIN("Role_ADMIN");
	
	private String value;
}
