package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDAO;
import dao.CartDetailsDAO;
import entities.Cart;
import entities.Cartdetail;

/**
 * Servlet implementation class CartServlet -- "/create", "/carts/store",
 */
@WebServlet({ "/carts/index", "/carts/delete", "/carts/edit", "/carts/update" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CartDAO crDAO;
	CartDetailsDAO crdtDAO;

	public CartServlet() {
		super();
		crDAO = new CartDAO();
		crdtDAO = new CartDetailsDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("index")) {
			this.index(request, response);
		} else if (uri.contains("create")) {
			this.create(request, response);
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
//	tạo hoá đơn trước, rồi thêm sp vào hoá đơn chi tiết, rồi thêm hoá đơn vào hoá đơn chi tiết
}
