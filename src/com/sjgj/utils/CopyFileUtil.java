package com.sjgj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

public class CopyFileUtil{

public static long CopyFileUtils(String fileName1,String fileName2) throws Exception{
	
	File f1 = new File(fileName1);
	File f2 = new File(fileName2);
	if (!f2.exists()){
		//不存在则创建
		f2.createNewFile();
	}else{
		f2.delete();
		f2.createNewFile();
	}
	
	  long time=new Date().getTime();
	  int length=2097152;
	  FileInputStream in=new FileInputStream(f1);
	  FileOutputStream out=new FileOutputStream(f2);
	  byte[] buffer=new byte[length];
	  while(true){
	   int ins=in.read(buffer);
	   if(ins==-1){
	    in.close();
	    out.flush();
	    out.close();
	    return new Date().getTime()-time;
	   }else
	    out.write(buffer,0,ins);
	  }
	 }
}