package com.cook.talk.model.naver;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class NaverLoginBO {

	private final static String Client_ID = "u6KAIfOc1yx4jmLjgUOp";
	private final static String Client_Secret = "umLqVBhv_e";
	private final static String REDIRECT_URL = "http://localhost/index";
	private final static String SESSION_STATE = "oauth_state";
	// 애플리케이션이 생성한 상태 토큰
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";

	public static String generateState() {
		// 난수 생성
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);

	}

	/* 네아로 인증 URL 생성 Method */
	public static String getAuthorizationUrl(HttpSession session) {
		
		// 세션 유효성 검증위해 난수 생성
		String state = generateState();
		session.setAttribute("state", state);

		/* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 */
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(Client_ID)
				.apiSecret(Client_Secret)
				.callback(REDIRECT_URL)
				.state(SESSION_STATE)
				.state(SESSION_STATE)
				.build(NaverLoginapi.instance());

		return oauthService.getAuthorizationUrl();
	}

	/* 네아로 Callback 처리 및 AccessToken 획득 Method */
	public static OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException {

		/* Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 */
		if (StringUtils.equals(SESSION_STATE, state)) {

			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(Client_ID)
					.apiSecret(Client_Secret)
					.callback(REDIRECT_URL)
					.state(state)
					.build(NaverLoginapi.instance());

			/* Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 */
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}
	/* 세션 유효성 검증을 위한 난수 생성기 */
	private String generateRandomString() {
	return UUID.randomUUID().toString();
	}
	/* http session에 데이터 저장 */
	private void setSession(HttpSession session,String state){
	session.setAttribute(SESSION_STATE, state);
	}
	/* http session에서 데이터 가져오기 */
	private String getSession(HttpSession session){
	return (String) session.getAttribute(SESSION_STATE);
	}

	/* Access Token을 이용하여 네이버 사용자 프로필 API를 호출 */
	public static String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {

		OAuth20Service oauthService = new ServiceBuilder().apiKey(Client_ID).apiSecret(Client_Secret)
				.callback(REDIRECT_URL).build(NaverLoginapi.instance());

		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
	}

}