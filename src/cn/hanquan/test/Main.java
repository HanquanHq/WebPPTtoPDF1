package cn.hanquan.test;

import java.io.IOException;

import cn.hanquan.utils.ConvertUtils;

public class Main {
	public static void main(String[] args) {
		try {
			ConvertUtils.ConvertPPTX("test.pptx",3,7,0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
