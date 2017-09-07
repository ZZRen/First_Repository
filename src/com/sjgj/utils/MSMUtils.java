package com.sjgj.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

//短信发送工具类
public class MSMUtils {
	

	private static String x_id = "sigj2016"; // 帐户
	private static String x_pwd = "rzz18236881897"; // 密码

	/**
	 * @param mobile
	 *            它是手机号
	 * @param content
	 *            内容
	 * @return 如果返回的是000 xxxx代表ok 其它是有问题，可以查看文档
	 * @throws UnsupportedEncodingException
	 */
	public static String SendSms(String mobile, String content) throws UnsupportedEncodingException {
		Integer x_ac = 10;// 发送信息
		HttpURLConnection httpconn = null;
		String result = "Error";
		StringBuilder sb = new StringBuilder();
		sb.append("http://service.winic.org:8009/sys_port/gateway/index.asp?"); // 这个是调用的接口

		// 以下是参数
		sb.append("id=").append(URLEncoder.encode(x_id, "gb2312"));
		sb.append("&pwd=").append(x_pwd);
		sb.append("&to=").append(mobile);
		sb.append("&content=").append(URLEncoder.encode(content, "gb2312"));
		sb.append("&time=").append("");
		try {
			URL url = new URL(sb.toString());
			httpconn = (HttpURLConnection) url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(httpconn.getInputStream()));
			result = rd.readLine();
			rd.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpconn != null) {
				httpconn.disconnect();
				httpconn = null;
			}

		}
		return result;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {

		String sendSms = SendSms("13716927641", "考研三件套 试卷版、精编版+阅读150基础训练篇 详情请戳：c.tb.cn/c.bbDp5");
		System.out.println(sendSms);
	}
}
