package cn.hanquan.utils;

import java.io.File;

public class FileNameUtils {
	/**
	 * 删除文件名后缀，获取真实文件名
	 * @param fileName 带后缀的文件名
	 * @return 删除后缀之后的文件名
	 */
	public static String getShortFileName(String fileName) {
		String shortFileName;
		if (fileName.endsWith("pptx")) {
			shortFileName = fileName.substring(0, fileName.length() - 5);// 去除后缀的文件名
		} else {
			shortFileName = fileName.substring(0, fileName.length() - 4);// 去除后缀的文件名
		}
		return shortFileName;
	}
	
	/**
	 * 创建用于存放pic的文件夹
	 * @param pptFilePath ppt文件的全路径名
	 */
	public static void createPicFolder(String pptFilePath) {
		File picSavePath = new File(pptFilePath);
		if (!picSavePath.exists()) {
			picSavePath.mkdirs();
		}
	}
}
