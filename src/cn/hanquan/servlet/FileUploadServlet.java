package cn.hanquan.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.hanquan.utils.PathManager;

/**
 * 接收上传的文件，将上传结果显示的字符串保存在session中，重定向到
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 从配置文件中读取保存路径
		String savePath = PathManager.PATH;

		// 接收post上传的文件
		String fileName = null;
		String result = null;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<?> items = upload.parseRequest(req);
			Iterator<?> iter = items.iterator();

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				fileName = item.getName();
				File savedFile = new File(savePath + fileName);
				// 检测文件是否符合要求 避免恶意文件
				if (fileName.endsWith(".ppt") || fileName.endsWith(".pptx")) {
					item.write(savedFile);
					result="true";
					System.out.println("文件存储的位置: " + savePath);
				} else {
					result="false";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 重定向
		HttpSession hs = req.getSession();
		hs.setAttribute("fileName", fileName);
		hs.setAttribute("result", result);
		resp.sendRedirect("/WebPPTtoPDF1/file/fileupload.jsp");
	}
}
