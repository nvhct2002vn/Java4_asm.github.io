package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import dao.CartDetailsDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import entities.Cart;
import entities.Cartdetail;
import entities.Category;
import entities.Product;

@WebServlet({ "/home", "/addprdtocard", "/cart", "/history", "/list-products", "/product", "/removePrdOnCart",
		"/locTheoDanhMuc" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO prdDAO;
	private CartDAO cartDAO;
	private CartDetailsDAO cartDTDAO;
	private CategoryDAO cateDAO;
	int tongTien = 0;
	int tongTienCu = 0;
	int tongTienMoi = 0;

	public HomeServlet() {
		super();
		prdDAO = new ProductDAO();
		cartDAO = new CartDAO();
		cartDTDAO = new CartDetailsDAO();
		cateDAO = new CategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("home")) {
			this.home(request, response);
		} else if (uri.contains("list-products")) {
			this.products(request, response);
		} else if (uri.contains("product")) {
			this.product(request, response);
		} else if (uri.contains("cart")) {
			this.cart(request, response);
		} else if (uri.contains("addprdtocard")) {
			System.out.println("123");
			this.addPrdToCard(request, response);
		} else if (uri.contains("removePrdOnCart")) {
			this.removePrdOnCart(request, response);
			System.out.println("xoá sản phẩm trong đơn hàng");
		} else if (uri.contains("locTheoDanhMuc")) {
			this.locTheoDanhMuc(request, response);
		} else if (uri.contains("history")) {
			this.history(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String uri = request.getRequestURI();
//		if (uri.contains("checklogin")) {
//			this.checkLogin(request, response);
//		} else if (uri.contains("store")) {
//			this.store(request, response);
//		}
	}

	public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> dsPrd = this.prdDAO.getAll();

		request.setAttribute("khoangTrang", " ");
		request.setAttribute("dsPrd", dsPrd);
		request.setAttribute("view", "/views/admin/home.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
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
		List<Category> dsCate = this.cateDAO.getAll();
		List<Product> dsPrd = this.prdDAO.getAll();

		request.setAttribute("khoangTrang", " ");
		request.setAttribute("dsPrd", dsPrd);
		request.setAttribute("dsCate", dsCate);
		request.setAttribute("view", "/views/admin/list-products.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	// Lọc sản phẩm theo loại sp
	public void locTheoDanhMuc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Product> dsPrd = this.prdDAO.getAllByIDCart(id);
		List<Category> dsCate = this.cateDAO.getAll();

		request.setAttribute("khoangTrang", " ");
		request.setAttribute("dsPrd", dsPrd);
		request.setAttribute("dsCate", dsCate);
		request.setAttribute("view", "/views/admin/list-products-filter.jsp");
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
				cartEntity.setTongTien(0);
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

			tongTien += (1 * product.getDonGia());
			cartEntity.setTongTien(tongTien);
			this.cartDAO.update(cartEntity);

			session.setAttribute("tongTien", tongTien);
			session.setAttribute("trangThaiButton", 0);
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/cart");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// xem rỏ hàng
	public void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		session.removeAttribute("hoaDonMoi");
//		System.out.println("Xoá hoá đơn thành công!");
		Cart cart = (Cart) session.getAttribute("hoaDonMoi");
		if (cart != null) {
			List<Cartdetail> lstCartdt = this.cartDTDAO.getAllByIDCart(cart.getId());
			request.setAttribute("lstCartdt", lstCartdt);
			request.setAttribute("khoangTrang", " ");
			request.setAttribute("idCart", cart.getId()); // lấy id để gán vào nút button
		}
		request.setAttribute("view", "/views/admin/cart.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

	}

	// xem lịch sử
	public void history(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		session.removeAttribute("hoaDonMoi");
		Cart cart = (Cart) session.getAttribute("hoaDonMoi");
		if (cart != null) {
			List<Cartdetail> lstCartdt = this.cartDTDAO.getAllByIDCart(cart.getId());
			request.setAttribute("lstCartdt", lstCartdt);
			request.setAttribute("khoangTrang", " ");
		}

		request.setAttribute("view", "/views/admin/history.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	// xoá sản phẩm trong đơn hàng
	public void removePrdOnCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("xoá sản phẩm trong đơn hàng");
		tongTienCu = (int) session.getAttribute("tongTien");

		int id = Integer.parseInt(request.getParameter("id"));
		Cartdetail entity = this.cartDTDAO.findByID(id);
		try {
			// sửa lại giá
			Cart cartEntity = (Cart) session.getAttribute("hoaDonMoi");
			tongTienMoi = tongTienCu - entity.getDonGia();
			cartEntity.setTongTien(tongTienMoi);
			System.out.println("Tiền mới: " + tongTienMoi);
			this.cartDAO.update(cartEntity);
			session.setAttribute("tongTien", tongTienMoi);

			// xoá sản phẩm
			this.cartDTDAO.delete(entity);
			session.setAttribute("message", "Xoá thành công");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/cart");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Xoá thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/cart");
		}
	}
}
