package controllers.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import beans.form_data.RegisterData;
import dao.UserDAO;
import entities.User;
import utils.EncryptUtil;
import utils.JpaUtil;

@WebServlet({
	"/users/index",
	"/users/create",
	"/users/store",
	"/users/edit",
	"/users/update",
	"/users/delete",
	"/users/show",
})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

    public UserServlet() {
        super();
        
        this.userDAO = new UserDAO();
    }

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		if (uri.contains("index")) {
			this.index(request, response);
		} else if (uri.contains("create")) {
			this.create(request, response);
		} else if (uri.contains("edit")) {
			this.edit(request, response);
		} else if (uri.contains("delete")) {
			this.delete(request, response);
		} else if (uri.contains("show")) {
			this.show(request, response);
		} else {
			// 404
		}
	}

	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		if (uri.contains("store")) {
			this.store(request, response);
		} else if (uri.contains("update")) {
			this.update(request, response);
		} else {
			// 404
		}
	}

	private void index(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		List<User> ds = this.userDAO.all();
		request.setAttribute("ds", ds);
		request.setAttribute("view",
			"/views/admin/users/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp")
			.forward(request, response);
	}
	
	private void create(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		request.setAttribute("view",
			"/views/admin/users/create.jsp");
		request.getRequestDispatcher("/views/layout.jsp")
			.forward(request, response);
	}
	
	private void store(
		HttpServletRequest request,
		HttpServletResponse response
	) throws IOException {
		HttpSession session = request.getSession();
		try {
			User entity = new User();
			BeanUtils.populate(entity,
				request.getParameterMap());
			
			String encrypted = EncryptUtil.encrypt(
				request.getParameter("password")
			);
			
			entity.setPassword(encrypted);
			this.userDAO.create(entity);
			session.setAttribute("message",
				"Thêm mới thành công");
			response.sendRedirect("/SP22B2_SOF3011_IT16302"
				+ "/users/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error",
					"Thêm mới thất bại");
			response.sendRedirect("/SP22B2_SOF3011_IT16302"
				+ "/users/create");
		}
	}
	
	private void edit(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		User entity = this.userDAO.findById(id);
		request.setAttribute("user", entity);
		
		request.setAttribute("view",
			"/views/admin/users/edit.jsp");
		request.getRequestDispatcher("/views/layout.jsp")
			.forward(request, response);
	}
	
	private void delete(
		HttpServletRequest request,
		HttpServletResponse response
	) throws IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		User entity = this.userDAO.findById(id);
		try {
			this.userDAO.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect("/SP22B2_SOF3011_IT16302"
			+ "/users/index");
	}
	
	private void show(
		HttpServletRequest request,
		HttpServletResponse response
	) {
		//
	}

	private void update(
		HttpServletRequest request,
		HttpServletResponse response
	) {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		User oldValue = this.userDAO.findById(id);
		
		User entity = new User();
		try {
			BeanUtils.populate(entity, request.getParameterMap());
			entity.setPassword( oldValue.getPassword() );
			
			this.userDAO.update(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
