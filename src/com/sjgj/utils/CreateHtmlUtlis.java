package com.sjgj.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CreateHtmlUtlis {
	public static void CreateHtml(String content,String title,String Url) throws IOException{
		if (content==null||content.equals("")) {
			
		}
		else {
			File file = new File(Url);
			if (!file.exists()){
				//不存在则创建
				file.createNewFile();
			}else{
				file.delete();
				file.createNewFile();
			}
			OutputStream out = new FileOutputStream(file);
		
			BufferedOutputStream bo=new BufferedOutputStream(out, 2048);
			bo.write(("<!DOCTYPE html><html><head><meta charset='UTF-8'><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>"+title+"</title></head><body>").getBytes("UTF-8"));
//			bo.flush();
			bo.write(content.getBytes("UTF-8"));
//			bo.flush();
			bo.write("</body></html>".getBytes("UTF-8"));
			bo.flush();
			out.close();
			bo.close();
			}
		}
	
}
