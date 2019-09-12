package cn.hanquan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hanquan.service.ConvertService;
import cn.hanquan.service.impl.ConvertServiceImpl;
import cn.hanquan.utils.FileNameUtils;

/**
 * 将ppt转化为pdf的servlet
 */
@WebServlet("/ConvertServlet")
public class ConvertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("myname");
		String shortFileName = FileNameUtils.getShortFileName(fileName);
		int num = Integer.parseInt(req.getParameter("choosenum"));
		int scale = Integer.parseInt(req.getParameter("scale"));

		ConvertService convertService = new ConvertServiceImpl();
		convertService.convert(fileName, num, scale);

		// 重定向
		String[] numIndex = { "1*2", "1*3", "2*4", "2*5", "3*7", "3*8" };
		String[] scaleIndex = { "4:3", "16:9" };
		
		HttpSession hs = req.getSession();
		hs.setAttribute("shortFileName", shortFileName);// 不含后缀文件名
		hs.setAttribute("fileName", fileName);// 含后缀文件名
		hs.setAttribute("num", numIndex[num]);// 排列方式
		hs.setAttribute("scale", scaleIndex[scale]);// ppt画幅比例
		
		resp.sendRedirect("/WebPPTtoPDF1/file/convert.jsp");
	}
}
