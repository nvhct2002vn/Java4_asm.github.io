package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import dao.CategoryDAO;
import dao.UserDAO;
import entities.Category;
import entities.User;

@WebServlet({ "/categories/index", "/categories/create", "/categories/store", "/categories/edit", "/categories/update",
		"/categories/delete" })
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;
	private CategoryDAO cateDAO;

	public CategoryServlet() {
		super();
		this.userDAO = new UserDAO();
		this.cateDAO = new CategoryDAO();
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
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("store")) {
			this.store(request, response);
		} else if (uri.contains("update")) {
			this.update(request, response);
		}
	}

	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> dsCate = this.cateDAO.getAll();

		request.setAttribute("dsCate", dsCate);
		request.setAttribute("view", "/views/admin/categories/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

	}

	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> dsUser = this.userDAO.getAll();
		request.setAttribute("dsUser", dsUser);

		request.setAttribute("view", "/views/admin/categories/create.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		Category category = new Category();
		String name = request.getParameter("ten");
		int id = Integer.parseInt(request.getParameter("user_id"));

		User user = userDAO.findByID(id);

		category.setTen(name);
		category.setUser(user);

		try {
			this.cateDAO.create(category);
			session.setAttribute("message", "Thêm thành công");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/categories/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Thêm thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/categories/create");
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Category entity = new Category();
			BeanUtils.populate(entity, request.getParameterMap());

			User user = (User) session.getAttribute("userLogin");

			entity.setUser(user);
			this.cateDAO.update(entity);
			session.setAttribute("message", "Sửa thành công");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/categories/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Sửa thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/categories/edit?id=" + id);
		}
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Category cate = this.cateDAO.findByID(id);

		request.setAttribute("cate", cate);
		request.setAttribute("view", "/views/admin/categories/edit.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Category cate = this.cateDAO.findByID(id);
		HttpSession session = request.getSession();
		try {
			this.cateDAO.delete(cate);
			session.setAttribute("message", "Xoá thành công");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/categories/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Xoá thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/categories/index");
		}
	}

}
