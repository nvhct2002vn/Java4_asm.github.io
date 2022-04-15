package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import dao.CartDetailsDAO;
import dao.ProductDAO;
import entities.Cart;
import entities.Cartdetail;
import entities.Category;
import entities.Product;

/**
 * Servlet implementation class CartServlet -- "/create", "/carts/store",
 */
@WebServlet({ "/carts/index", "/carts/delete", "/carts/edit", "/carts/status", "/carts/update", "/dathang",
		"/huydonhang" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CartDAO crDAO;
	CartDetailsDAO crdtDAO;
	ProductDAO prdDAO;

	public CartServlet() {
		super();
		crDAO = new CartDAO();
		crdtDAO = new CartDetailsDAO();
		prdDAO = new ProductDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("index")) {
			this.index(request, response);
		} else if (uri.contains("create")) {
			this.create(request, response);
		} else if (uri.contains("edit")) {
			this.edit(request, response);
		} else if (uri.contains("delete")) {
			this.delete(request, response);
		} else if (uri.contains("status")) {
			this.status(request, response);
		} else if (uri.contains("dathang")) {
			this.dathang(request, response);
		} else if (uri.contains("huydonhang")) {
			this.huydonhang(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	// hiểm thị danh sách hoá đơn
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cart> lstCart = this.crDAO.getAll();

		request.setAttribute("lstCart", lstCart);
		request.setAttribute("view", "/views/admin/carts/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cart> lstCart = this.crDAO.getAll();
		List<Cartdetail> lstCartdt = this.crdtDAO.getAll();

		request.setAttribute("lstCart", lstCart);
		request.setAttribute("lstCartdt", lstCartdt);
		request.setAttribute("view", "/views/admin/carts/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	// xem chi tiết hoá đơn
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Cartdetail> lstCartdt = this.crdtDAO.getAllByIDCart(id);
		Cart Cart = this.crDAO.findByID(id);

		request.setAttribute("lstCartdt", lstCartdt);
		request.setAttribute("Cart", Cart);
		request.setAttribute("khoangTrang", " ");
		request.setAttribute("view", "/views/admin/carts/edit.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

// xoá hoá đơn
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		Cart entity = this.crDAO.findByID(id);
		List<Cartdetail> listCart = this.crdtDAO.getAllByIDCart(id);
		try {
			// xoá hết cartdetails trong carts
			for (Cartdetail cartdetail : listCart) {
				this.crdtDAO.delete(cartdetail);
			}

			this.crDAO.delete(entity);
			session.setAttribute("message", "Xoá thành công");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/carts/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Xoá thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/carts/index");
		}
	}

	// Xác nhận đơn hàng
	public void status(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Cart entity = this.crDAO.findByID(id);

		HttpSession session = request.getSession();
		try {
			entity.setTrangThai(2);

			this.crDAO.update(entity);
			session.setAttribute("message", "Xác nhận hoá đơn thành công");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/carts/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Xác nhận hoá đơn thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/carts/index");
		}
	}

	// Đặt đơn hàng
	public void dathang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Cart entity = this.crDAO.findByID(id);
		HttpSession session = request.getSession();
		try {

			entity.setTrangThai(1);

			this.crDAO.update(entity);
			session.setAttribute("message", "Đặt hàng thành công");
			session.setAttribute("trangThaiButton", 1);
			request.setAttribute("Cart", entity);
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/cart");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Đặt hàng thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/cart");
		}
	}

	public void huydonhang(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Cart entity = this.crDAO.findByID(id);
		HttpSession session = request.getSession();
		try {

			entity.setTrangThai(0);

			this.crDAO.update(entity);
			session.setAttribute("message", "Huỷ đặt hàng thành công");
			session.setAttribute("trangThaiButton", 0);

			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/cart");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Huỷ đặt hàng thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/cart");
		}
	}
}
