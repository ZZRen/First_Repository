package com.sjgj.utils;

import java.io.IOException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Utlis {
	
   	public static void main(String[] args) {
        		String str = "10000005";
        		String ret = null;
        		ret = new BASE64Encoder().encode(str.getBytes());
        		System.out.println("加密前:"+str+" 加密后:"+ret);
        		
        		
        		str = "MTAwMDA2NzI=";
        		try {
        			ret = new String(new BASE64Decoder().decodeBuffer(str));
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
    		    System.out.println("解密前:"+str+" 解密后:"+ret);
    	}
}
