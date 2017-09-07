package com.sjgj.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sjgj.utils.Constants;

public class LoginInterceptor implements HandlerInterceptor {
	

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 只拦截以/buyer开头的请求资源
		String uri = request.getRequestURI();
		System.out.println(uri);
		if((uri.startsWith("/sjgjsend/user"))||(uri.startsWith("/sjgjsend/login"))||(uri.startsWith("/sjgjsend/userlogin"))){ 
			return true;
			}
		else {
			// 以/buyer开头的请求资源需要拦截----判断用户是否登录
//				String backusername = (String)jedisService.getAttributeForUserName(RequestUtils.getCSESSIONID(request, response));
//			if(backusername == null){ // 未登录，踢回到登录页面
//				response.sendRedirect("http://localhost:8080/login.action");
//				return false;
//		}
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null && cookies.length > 0){
				for (Cookie cookie : cookies) { // key-value
					if("CSEESIONID".equals(cookie.getName())){
						String backusername=cookie.getValue();
						if (backusername!=null) {
							return true;
						}else {
							response.sendRedirect(Constants.HTTP_NETURL+"sjgjsend/login.action");
							return false;
						}
					}
				}
				response.sendRedirect(Constants.HTTP_NETURL+"sjgjsend/login.action");
				return false;
			}
			else {
				 // 未登录，踢回到登录页面
					response.sendRedirect(Constants.HTTP_NETURL+"sjgjsend/login.action");
					return false;
				
		}
		}
//		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
