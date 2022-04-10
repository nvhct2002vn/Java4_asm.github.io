package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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

@WebServlet({ "/users/index", "/users/create", "/users/edit", "/users/store", "/users/update", "/users/delete" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;

	public UserServlet() {
		super();
		this.userDao = new UserDAO();
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
		List<User> ds = this.userDao.getAll();
		
		request.setAttribute("ds", ds);
		request.setAttribute("view", "/views/admin/users/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("view", "/views/admin/users/create.jsp");
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

			this.userDao.create(entity); // thêm mới

			session.setAttribute("message", "Thêm mới thành công");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/users/index");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			session.setAttribute("erro", "Thêm mới thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/users/create");
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		int id = Integer.parseInt(request.getParameter("id"));
		User user = this.userDao.findByID(id);
		try {
			User entity = new User();
			BeanUtils.populate(entity, request.getParameterMap());
			entity.setPassword(user.getPassword());
			this.userDao.update(entity);
			session.setAttribute("message", "Sửa thành công");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/users/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Sửa thất bại");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/users/edit?id=" + id);
		}
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		User user = this.userDao.findByID(id);

		request.setAttribute("user", user);
		
		request.setAttribute("view", "/views/admin/users/edit.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		User user = this.userDao.findByID(id);
		try {
			this.userDao.delete(user);
			session.setAttribute("message", "Xoá thành công");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Xoá thất bại");
		}
		response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/users/index");
	}

}
