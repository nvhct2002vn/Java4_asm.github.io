package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import dao.CartDetailsDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entities.Cart;
import entities.Cartdetail;
import entities.Product;
import entities.User;
import utils.EncryptUtil;
//"addprdtocard", 
@WebServlet({ "/home", "/cart", "/list-products", "/product", "/accounts/login", "/accounts/register",
		"/accounts/store", "/accounts/checklogin", "/accounts/logout" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO prdDAO;
	private UserDAO userDAO;
	CartDetailsDAO crdtDAO;

	public HomeServlet() {
		super();
		prdDAO = new ProductDAO();
		userDAO = new UserDAO();
		crdtDAO = new CartDetailsDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("home")) {
			this.home(request, response);
		} else if (uri.contains("login")) {
			this.login(request, response);
		} else if (uri.contains("register")) {
			this.register(request, response);
		} else if (uri.contains("logout")) {
			this.logout(request, response);
		} else if (uri.contains("list-products")) {
			this.products(request, response);
		} else if (uri.contains("product")) {
			this.product(request, response);
		} else if (uri.contains("cart")) {
			this.cart(request, response);
		}
//		else if (uri.contains("addprdtocard")) {
//			this.addPrdToCard(request, response);
//		}
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

	public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> dsPrd = this.prdDAO.getAll();

		request.setAttribute("khoangTrang", " ");
		request.setAttribute("dsPrd", dsPrd);
		request.setAttribute("view", "/views/admin/home.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
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

	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("userLogin");
		response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/home");
	}

	public void product(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> dsPrd = this.prdDAO.getAll();
		int idPrd = Integer.parseInt(request.getParameter("id"));
		Product prd = this.prdDAO.findByID(idPrd);

		request.setAttribute("khoangTrang", " ");
		request.setAttribute("dsPrd", dsPrd);
		request.setAttribute("prd", prd);
		request.setAttribute("view", "/views/admin/product.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	public void products(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> dsPrd = this.prdDAO.getAll();

		request.setAttribute("khoangTrang", " ");
		request.setAttribute("dsPrd", dsPrd);
		request.setAttribute("view", "/views/admin/list-products.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	// thêm sp vào rỏ hàng
//	public void addPrdToCard(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		List<Cartdetail> lstCartdt = this.crdtDAO.getAll();
//
//		request.setAttribute("lstCartdt", lstCartdt);
//		request.setAttribute("view", "/views/admin/cart.jsp");
//		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
//	}

	public void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cartdetail> lstCartdt = this.crdtDAO.getAll();

		request.setAttribute("lstCartdt", lstCartdt);
		request.setAttribute("view", "/views/admin/cart.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

}
