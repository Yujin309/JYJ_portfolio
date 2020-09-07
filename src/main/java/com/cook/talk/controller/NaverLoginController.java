package com.cook.talk.controller;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cook.talk.model.naver.NaverLoginBO;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.nimbusds.oauth2.sdk.ParseException;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Controller
public class NaverLoginController {

	/*@RequestMapping(value = "login.do")
	public String initLogin(Model model, HttpSession session) throws Exception {

		return "login.all";
		// 생성한 인증 url를 모델에 담아
	}*/

	
	
	// 네이버 Callback호출 메소드
	@RequestMapping(value = "/Callback" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String Callback(ModelMap model, @RequestParam String code, @RequestParam String state,
			HttpSession session) throws IOException, net.minidev.json.parser.ParseException {

		System.out.println("콜백구조");
		// 네아로 인증이 성공적으로 완료되면 CODE파라미터가 전달되어 이를 통해 쩝근 토큰을 발급

		  /* 네아로 인증이 성공적으로 완료되면 code 파라미터가 전달되며 이를 통해 access token을 발급 */
	    OAuth2AccessToken oauthToken = NaverLoginBO.getAccessToken(session, code, state);
	    
	    //로그인 사용자 정보 읽어오기 (spring 형식의 json데이터 )
	    String apiResult = NaverLoginBO.getUserProfile(oauthToken);

	    
	    //string 형식인 apiresult를 json형태로 바꿈 
	    
	    JSONParser parser=new JSONParser();
	    Object obj =parser.parse(apiResult);
	    JSONObject jsonObject=(JSONObject) obj;
	    
	    
	    //데이터 파싱 
	    JSONObject response_obj = (JSONObject)jsonObject.get("response");
	  //response의 nickname값 파싱
	  String nickname = (String)response_obj.get("nickname");
	  System.out.println(nickname);

	    
	    System.out.println("Naver login success");
	    session.setAttribute("sessionId", nickname);//세션 생성 
	    
	    model.addAttribute("result", apiResult);

		return "/index";
	}


}