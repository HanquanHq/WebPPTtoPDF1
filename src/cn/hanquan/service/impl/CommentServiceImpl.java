package cn.hanquan.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.hanquan.mapper.CommentMapper;
import cn.hanquan.pojo.Comment;
import cn.hanquan.pojo.Pageinfo;
import cn.hanquan.service.CommentService;

public class CommentServiceImpl implements CommentService {

	@Override
	public Pageinfo showPage(int pageSize, int pageNum) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		CommentMapper logMapper = session.getMapper(CommentMapper.class);

		Pageinfo pageInfo = new Pageinfo();
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);

		// 查询结果
		int pageStart = pageSize * (pageNum - 1);
		List<Comment> list = logMapper.selByPage(pageStart, pageSize);
		pageInfo.setDataList(list);
		System.out.println("ContentService中的list:" + list);

		// 总页数
		int count = logMapper.selCount();
		pageInfo.setTotal((count % pageSize == 0 ? count / pageSize : count / pageSize + 1));
		System.out.println("ContentService中的count:" + count);

		session.close();
		return pageInfo;
	}

}
