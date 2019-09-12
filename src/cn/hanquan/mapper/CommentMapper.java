package cn.hanquan.mapper;

import java.util.List;

import cn.hanquan.pojo.Comment;

public interface CommentMapper {

	/**
	 * 插入一条评论
	 * @param cont 评论内容
	 * @param name 姓名
	 * @param createtime 创建时间
	 * @return 影响的行数
	 */
	int insComment(String cont,String name,String createtime);

	/**
	 * 分页查询数据
	 * @param map 表示参数的键值对
	 * @return
	 */
	List<Comment> selByPage(int pageStart, int pageSize);
	
	/**
	 * 查询总记录条数
	 * @return 总记录条数
	 */
	int selCount();
	
}
