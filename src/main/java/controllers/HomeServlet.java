package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.beanutils.BeanUtils;

import dao.CartDAO;
import dao.CartDetailsDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entities.Cart;
import entities.Cartdetail;
import entities.Product;
import entities.User;
import utils.EncryptUtil;

@WebServlet({ "/home", "/addprdtocard", "/cart", "/list-products", "/product", "/accounts/login", "/accounts/register",
		"/accounts/store", "/accounts/checklogin", "/accounts/logout" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO prdDAO;
	private UserDAO userDAO;
	private CartDAO cartDAO;
	private CartDetailsDAO cartDTDAO;
	boolean check = false;

	public HomeServlet() {
		super();
		prdDAO = new ProductDAO();
		userDAO = new UserDAO();
		cartDAO = new CartDAO();
		cartDTDAO = new CartDetailsDAO();
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
		} else if (uri.contains("addprdtocard")) {
			System.out.println("123");
			this.addPrdToCard(request, response);
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

	// hiển thị sản phẩm
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

	// hiển thị danh sách sản phẩm
	public void products(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> dsPrd = this.prdDAO.getAll();

		request.setAttribute("khoangTrang", " ");
		request.setAttribute("dsPrd", dsPrd);
		request.setAttribute("view", "/views/admin/list-products.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	// thêm sp vào rỏ hàng
	public void addPrdToCard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Cart cartEntity = (Cart) session.getAttribute("hoaDonMoi");
			// tạo hoá đơn
			if (cartEntity == null) {
				cartEntity = new Cart();
				Date now = new Date();
				cartEntity.setNgayMua(now);
				cartEntity.setTongTien(8888888);
				cartEntity.setTrangThai(0);
				this.cartDAO.create(cartEntity);
				session.setAttribute("hoaDonMoi", cartEntity);
				System.out.println("Tạo hoá đơn thành công!");
			} else {
				System.out.println("Hoá đơn đã tồn tại: " + session.getAttribute("hoaDonMoi"));
			}
			// lấy ra sản phẩm được chọn
			int idPrd = Integer.parseInt(request.getParameter("id"));
			Product product = this.prdDAO.findByID(idPrd);

			// lấy ra đối tượng trong session và gán vào đối tượng mới
			Cart cart = (Cart) session.getAttribute("hoaDonMoi");

			// tạo hoá đơn chi tiết mới
			Cartdetail cartdetail = new Cartdetail();
			cartdetail.setCart(cart);
			cartdetail.setProduct(product);
			cartdetail.setDonGia(product.getDonGia());
			cartdetail.setSoLuong(1);
			this.cartDTDAO.create(cartdetail);
			System.out.println("Tạo thành công hoá đơn chi tiết!");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/cart");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// không lấy được id của khi tạo session
	}

	// xem rỏ hàng
	public void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("hoaDonMoi");
		if (cart != null) {
			List<Cartdetail> lstCartdt = this.cartDTDAO.getAllByIDCart(cart.getId());
			request.setAttribute("lstCartdt", lstCartdt);
		}

		request.setAttribute("view", "/views/admin/cart.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

}
