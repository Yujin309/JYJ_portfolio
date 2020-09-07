package com.cook.talk.model.naver;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverLoginapi extends DefaultApi20 {

	protected NaverLoginapi() {

	}

	private static class InstanceHolder {
		private static final NaverLoginapi INSTANCE = new NaverLoginapi();
	}

	public static NaverLoginapi instance() {
		return InstanceHolder.INSTANCE;

	}

	@Override
	public String getAccessTokenEndpoint() {
		// TODO Auto-generated method stub
		return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		// TODO Auto-generated method stub
		return "https://nid.naver.com/oauth2.0/authorize";
	}

}
