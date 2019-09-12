package cn.hanquan.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 从properties中读取文件路径的配置信息，并加载至内存中
 * @author Buuug
 *
 */
public class PathManager {
	/**
	 * 从properties文件中读取的保存路径
	 */
	public static String PATH;
	
	static {
		Properties pros = new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("path.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		PATH=pros.getProperty("savePath");
	}
}
