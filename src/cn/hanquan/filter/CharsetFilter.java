package cn.hanquan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharsetFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 设置编码格式
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//resp.setContentType("charset=utf-8");//这句话加上会误伤css

		System.out.println("字符集过滤器：开始");
		// 放行
		chain.doFilter(req, resp);
		System.out.println("字符集过滤器：结束");
	}

	@Override
	public void destroy() {		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
