package cn.hanquan.service;

import java.io.IOException;

public interface PageInfoService {
	/**
	 * 显示详细转账信息：包含账户对应姓名
	 * @return
	 * @throws IOException
	 */
	void addComInfo(String cont, String name,String createtime) throws IOException;
}
