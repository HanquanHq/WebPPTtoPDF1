package cn.hanquan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hanquan.pojo.Pageinfo;
import cn.hanquan.service.CommentService;
import cn.hanquan.service.impl.CommentServiceImpl;

/**
 * 分页显示评论
 * @author Buuug
 *
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommentService commentService = new CommentServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 第一次访问的验证,如果没有传递参数,设置默认值
		String pageSizeStr = req.getParameter("pageSize");
		int pageSize = 3;
		if (pageSizeStr != null && !pageSizeStr.equals("")) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		String pageNumberStr = req.getParameter("pageNum");
		int pageNum = 1;
		if (pageNumberStr != null && !pageNumberStr.equals("")) {
			pageNum = Integer.parseInt(pageNumberStr);
		}
		
		Pageinfo pi = commentService.showPage(pageSize, pageNum);
		System.out.println(pi.getDataList());
		req.setAttribute("Pageinfo", pi);
		req.getRequestDispatcher("/comments/result.jsp").forward(req, resp);
	}
}
