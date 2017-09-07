package com.sjgj.utils;

import java.util.Random;

public class VerificateCodeUtils {
	public static String createCode() {
		Random random = new Random();
		String code = "";
		for (int i = 0; i < 6; i++) {
			Integer r= random.nextInt(9);
			code += r.toString();
		}
		return code;

	}
	public static void main(String[] args) {
		String code = createCode();
		System.out.println(code);
	}

}
