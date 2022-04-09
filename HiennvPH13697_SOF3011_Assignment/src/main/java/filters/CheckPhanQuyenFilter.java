package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;

@WebFilter(urlPatterns = { "/users/*", "/products/*", "/categories/*", })
public class CheckPhanQuyenFilter implements Filter {

	public CheckPhanQuyenFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse httpRes = (HttpServletResponse) response;
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();

		User user = (User) session.getAttribute("userLogin"); // lấy ra user từ session lúc đăng nhập

		//user == null && 
		if (user.getPhanQuyen() == 0) {
			httpRes.sendRedirect("/HiennvPH13697_SOF3011_Assignment/home");
			return;
		}

		chain.doFilter(request, response); // kiểm tra
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
