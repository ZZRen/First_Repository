package com.sjgj.utils;

/**
 * 
 * @ClassName: Constants
 * @Company: http://www.sjgj.com/
 * @Description: 维护所有的常量信息
 * @author RZZ
 * @date 2016年10月10日 上午10:56:39
 */
public interface Constants {
	
	public static final String HTTP_NETURL = "http://192.168.1.70:8080/"; 		// 网络服务器地址
	public static final String HTTP_URL = "http://192.168.1.70:8080/jane/"; 		// 网络文件服务器地址
	public static final String LOCAL_URL = "D:/JANE/"; 			                //	 本地文件服务器地址

	//域名绑定的网络地址
//	public static final String HTTP_NETURL = "http://increment.kaoyanren.com:8084/"; 		// 网络服务器地址
//	public static final String HTTP_URL = "http://increment.kaoyanren.com:8084/jane/"; 		// 网络文件服务器地址
//	public static final String LOCAL_URL = "/home/appfolder/"; 	
//	
	
	public static final String USER_SESSION = "USER_SESSION"; 						// 用户标识
	
	public static final String LOCALAPPCONTENT_URL =LOCAL_URL+ "appcontent/";			// 服务器app内容本地地址
	public static final String HTTP_APPCONENT_URL = HTTP_URL+"appcontent/";            // 服务器app内容网络地址
	
	public static final String LOCALUPLOAD_URL = LOCAL_URL+"upload/"; 				// 后台上传图片本地服务器地址
	public static final String HTTP_UPLOAD_URL = HTTP_URL+"upload/"; 				// 后台上传图片网络地址
	
	public static final String LOCACSVDATE_COMPRESSQUESTION_URL = LOCAL_URL+"csvdate/"; 	// 上传csv数据本地服务器地址
	public static final String HTTP_CSVDATE_COMPRESSQUESTION_URL = HTTP_URL+"csvdate/"; 	// 上传sv数据网络服务器地址
	
}
