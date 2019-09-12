package cn.hanquan.service;

import java.io.IOException;

public interface ConvertService {
	/**
	 * 将PPT转化为PDF
	 * @param fileName PPT文件名(带后缀)
	 * @param num 选择的排列方式索引(可取值:0-5)
	 * @param scale ppt画幅(可取值:0,1)
	 * @throws IOException
	 */
	public void convert(String fileName, int num, int scale) throws IOException;
}
