package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import dao.UserDAO;
import entities.User;
import utils.EncryptUtil;
@WebServlet({ "/accounts/login", "/accounts/register", "/accounts/store", "/accounts/checklogin", "/accounts/logout" })
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public AccountServlet() {
		super();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("login")) {
			this.login(request, response);
			System.out.println("Account");
		} else if (uri.contains("register")) {
			this.register(request, response);
			System.out.println("Dăng ký");
		} else if (uri.contains("logout")) {
			this.logout(request, response);
			System.out.println("Đăng xuất");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("checklogin")) {
			this.checkLogin(request, response);
		} else if (uri.contains("store")) {
			this.store(request, response);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		request.setAttribute("view", "/views/admin/accounts/login.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

	}

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		request.setAttribute("view", "/views/admin/accounts/register.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

	}

	// đăng ký
	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User entity = new User();
		try {
			BeanUtils.populate(entity, request.getParameterMap());
			String encrypted = EncryptUtil.encrypt(request.getParameter("password")); // lấy password và mã hoá
			entity.setPassword(encrypted);

			this.userDAO.create(entity); // thêm mới

			session.setAttribute("message", "Đăng ký thành công");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/accounts/login");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			session.setAttribute("erro", "Đăng ký thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/accounts/register");
		}
	}

	// kiểm tra trạng thái đăng nhập
	public void checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String email = request.getParameter("email"), pwd = request.getParameter("password");

		User user = this.userDAO.findByEmail(email);
		boolean check = EncryptUtil.check(pwd, user.getPassword());

		if (check == true) {
			// Đăng nhập thành công
			session.setAttribute("userLogin", user); // tạo userLogin để dùng filter
//			session.setAttribute("message", "Đăng nhập thành công");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/home");
		} else {
			// Đăng nhập thất bại
			session.setAttribute("erro", "Đăng nhập thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/accounts/login");
//			request.setAttribute("view", "/views/admin/acccounts/login.jsp");
//			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		}
	}

	// đăng xuất
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("userLogin");
		response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/home");
	}

}
