package cn.hanquan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hanquan.service.PageInfoService;
import cn.hanquan.service.impl.PageInfoServiceImpl;

@WebServlet("/PublishServlet")
public class PublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 从页面中获取信息
		String name = req.getParameter("name");// 姓名
		String cont = req.getParameter("cont");// 评论内容
		String createtime = req.getParameter("hideTime");// 创建时间
		if (cont == null) {
			cont = "";
		}

		PageInfoService contentInfoService = new PageInfoServiceImpl();
		contentInfoService.addComInfo(cont, name, createtime);

		// 重定向
		resp.sendRedirect("/WebPPTtoPDF1/ShowServlet");
	}
}
