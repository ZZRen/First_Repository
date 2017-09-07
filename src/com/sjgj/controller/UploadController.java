package com.sjgj.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sjgj.utils.Constants;

@Controller
public class UploadController {
			
			//上传序列号txt文件
	   @RequestMapping("uploadcsv.action")  
	    public void uploadtxt( MultipartFile csv, HttpServletRequest request,HttpServletResponse response) {  
	  
	       String path = Constants.LOCACSVDATE_COMPRESSQUESTION_URL;			//上传文件服务器的本地地址
	        String fileName =UUID.randomUUID().toString().substring(0, 8)+csv.getOriginalFilename();  
//	        String fileName = csv.getOriginalFilename();
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        //保存  
	        try {  
	        	csv.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        String newpath=path+fileName;
	         
	        JSONObject jsonObject = new JSONObject();
			jsonObject.put("csvUrl", newpath);
			jsonObject.put("msg", "上传成功")	;
			response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().write(jsonObject.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    } 
	
}
