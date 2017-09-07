package com.sjgj.utils;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName: RequestUtils
 * @Company: http://www.itcast.cn/
 * @Description: 维护票据的工具类
 * @author JD 
 * @date 2016年10月24日 上午10:24:49
 */
public class RequestUtils {
	
	public static String getCSESSIONID(HttpServletRequest request, HttpServletResponse response){
		
		// 1、从cookie中获取
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0){
			for (Cookie cookie : cookies) { // key-value
				if("CSEESIONID".equals(cookie.getName())){
					return cookie.getValue();
				}
			}
		}
		
		// 2、如果cookie中没有key，自己生成并且保存到cookie中
		String CSEESIONID = UUID.randomUUID().toString().replace("-", "");
		Cookie cookie = new Cookie("CSEESIONID", CSEESIONID);
		cookie.setMaxAge(60*60);
		cookie.setPath("/");
		response.addCookie(cookie); // cookie保存到本地
		
		return CSEESIONID;
	}

}
