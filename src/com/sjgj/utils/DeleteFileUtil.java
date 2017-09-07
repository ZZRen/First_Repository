package com.sjgj.utils;

import java.io.File;

/**
 * 删除文件和目录
 *
 */
public class DeleteFileUtil {

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
//                System.out.println("删除单个文件" + fileName + "成功！");
//                return true;
            } else {
//                System.out.println("删除单个文件" + fileName + "失败！");
//                return false;
            }
        } else {
//            System.out.println("删除单个文件失败：" + fileName + "不存在！");
//            return false;
        }
    }
    
    public static void main(String[] args) {
//    	deleteFile("C:/Users/dell/Desktop/111.png");
    	String pictureUrl = "C:/Users/dell/Desktop/111.png";
    	System.out.println(pictureUrl.substring(pictureUrl.lastIndexOf("/")+1));
	}
}
