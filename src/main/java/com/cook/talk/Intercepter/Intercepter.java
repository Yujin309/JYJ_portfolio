package com.cook.talk.Intercepter;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cook.talk.model.VO.UserVO;
import com.cook.talk.model.dao.MainDAO;

@Component
public class Intercepter extends HandlerInterceptorAdapter{
	@Autowired
	MainDAO maindao;
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) {
		HttpSession session=request.getSession();
		session.setAttribute("total", maindao.totalSelect());
		if(SecurityContextHolder.getContext().getAuthentication()!=null) {
			String userID=SecurityContextHolder.getContext().getAuthentication().getName();
			session.setAttribute("userIDD", userID);
			session.setAttribute("userPic11", maindao.selectUserPic(userID));
			System.out.println("asldjasjdlasjd여기"+maindao.selectUserPic(userID));
		}
		return true;
	}
	
	@Override
	public void postHandle( HttpServletRequest request,
							HttpServletResponse response,
							Object handler,
							ModelAndView modelAndView) {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, 
								Object handler, 
								Exception ex) {
	}
}
