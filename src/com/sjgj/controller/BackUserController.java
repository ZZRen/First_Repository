package com.sjgj.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjgj.pojo.BackUser;
import com.sjgj.service.BackUserService;

@Controller
public class BackUserController {
	@Resource
	private BackUserService backUserService;

	@RequestMapping("/login.action")      
	public String login() {
		return "login";
}
	@RequestMapping("/userlogin.action")      
	public String userlogin(String backusername,String password,Model model,
			HttpServletRequest request, HttpServletResponse response) {
		// 1、判读用户名是否为空
		if(backusername != null && !"".equals(backusername)){
			// 2、用户名必须正确
			 BackUser backuser = backUserService.selectBuyerByUserName(backusername);
			if(backuser != null){
				// 3、密码不能为空
				if(password != null && !"".equals(password)){
					// 4、判读密码是否正确
					if(backuser.getPassword().equals(password)){
						// 校验通过 1、保存用户到redis中；2、返回登录前页面
//						jedisService.setAttributeForUserName(RequestUtils.getCSESSIONID(request, response), backusername);
					
						// 2、如果cookie中没有key，自己生成并且保存到cookie中
//						String CSEESIONID = UUID.randomUUID().toString().replace("-", "");
						Cookie cookie = new Cookie("CSEESIONID", backusername);
//						cookie.setMaxAge(60*60);
						cookie.setPath("/");
						response.addCookie(cookie); // cookie保存到本地
						return "redirect:back/index.action";
//						return "forward:back/index.action";
					}else{
						model.addAttribute("user", backusername);
						model.addAttribute("error", "用户名或密码错误");
					}
				}else{
					model.addAttribute("user", backusername);
					model.addAttribute("error", "用户名或密码错误");
				}
			}else{
				model.addAttribute("user", backusername);
				model.addAttribute("error", "用户名或密码错误");
			}
		}else{
			model.addAttribute("user", backusername);
			model.addAttribute("error", "用户名或密码错误");
		}
		return "login";
}
}
