package com.cook.talk.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration

public class Handler implements AuthenticationSuccessHandler{
//로그인 성공 처리 핸들러가 상속받는 인터페이스 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		//로그인 성공시 필요한 작업 추가 한 것
		
	 //권한 부여,제어 즉 사용권한 . 
		List<GrantedAuthority> authorities = (List<GrantedAuthority>)authentication.getAuthorities();
	String strAuth = authorities.get(0).getAuthority();
	
	if(strAuth.equals(0)) {
		response.sendRedirect(request.getContextPath()+"/admin");
		
	}else {
		response.sendRedirect(request.getContextPath()+"/user");
	}
	
			System.out.println(((Authentication) authentication.getDetails()).getAuthorities());
		}

}
