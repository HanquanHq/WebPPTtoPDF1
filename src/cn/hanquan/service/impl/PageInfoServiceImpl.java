package cn.hanquan.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.hanquan.mapper.CommentMapper;
import cn.hanquan.service.PageInfoService;

public class PageInfoServiceImpl implements PageInfoService {

	@Override
	public void addComInfo(String cont, String name,String createtime) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();

		CommentMapper comMapper = session.getMapper(CommentMapper.class);
		int res = comMapper.insComment(cont, name, createtime);
		System.out.println("ContentInfoServiceImpl.addComInfo():" + res);
		session.commit();
		session.close();
	}
}
