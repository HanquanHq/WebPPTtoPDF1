package cn.hanquan.service.impl;

import java.io.IOException;

import cn.hanquan.service.ConvertService;
import cn.hanquan.utils.ConvertUtils;
/**
 * 将ppt转化为pdf的业务层实现类
 * @author Buuug
 *
 */
public class ConvertServiceImpl implements ConvertService {

	@Override
	public void convert(String fileName, int num, int scale) throws IOException {
		if (fileName.endsWith(".pptx")) {
			switch (num) {
			case 0:
				ConvertUtils.ConvertPPTX(fileName, 1, 2, scale);
				break;
			case 1:
				ConvertUtils.ConvertPPTX(fileName, 1, 3, scale);
				break;
			case 2:
				ConvertUtils.ConvertPPTX(fileName, 2, 4, scale);
				break;
			case 3:
				ConvertUtils.ConvertPPTX(fileName, 2, 5, scale);
				break;
			case 4:
				ConvertUtils.ConvertPPTX(fileName, 3, 7, scale);
				break;
			case 5:
				ConvertUtils.ConvertPPTX(fileName, 3, 8, scale);
				break;
			}
		} else if (fileName.endsWith(".ppt")) {
			switch (num) {
			case 0:
				ConvertUtils.ConvertPPT(fileName, 1, 2, scale);
				break;
			case 1:
				ConvertUtils.ConvertPPT(fileName, 1, 3, scale);
				break;
			case 2:
				ConvertUtils.ConvertPPT(fileName, 2, 4, scale);
				break;
			case 3:
				ConvertUtils.ConvertPPT(fileName, 2, 5, scale);
				break;
			case 4:
				ConvertUtils.ConvertPPT(fileName, 3, 7, scale);
				break;
			case 5:
				ConvertUtils.ConvertPPT(fileName, 3, 8, scale);
				break;
			}
		} else {
			System.out.println("上传的不是ppt。前端已阻值按钮点击，理论上不应该走到这里");
		}
	}
}
