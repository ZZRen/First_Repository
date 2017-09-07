package com.sjgj.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjgj.service.BackUserService;
@Controller
@RequestMapping("back")
public class BackController {
	@Resource
	private BackUserService backUserService;
	@RequestMapping("index.action")      
	public String index() {
		return "index";
}
	@RequestMapping("top.action")      
	public String top() {
		return "top";
}
	@RequestMapping("main.action")       
	public String main() {
		return "main";
}
	@RequestMapping("left.action")       
	public String left() {
		return "left";
}
	@RequestMapping("right.action")      
	public String right() {
		return "right";
}
}
