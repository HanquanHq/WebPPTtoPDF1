package cn.hanquan.service;

import java.io.IOException;

import cn.hanquan.pojo.Pageinfo;

public interface CommentService {
	/**
	 * 分页显示
	 * 
	 * @param pageSize 一页显示的记录条数
	 * @param pageNum  当前第几页
	 * @return 此页中所有数据
	 * @throws IOException
	 */
	Pageinfo showPage(int pageSize, int pageNum) throws IOException;
}
