package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import dao.CategoryDAO;
import dao.ProductDAO;
import entities.Category;
import entities.Product;
import entities.User;

@MultipartConfig
@WebServlet({ "/products/index", "/products/create", "/products/store", "/products/update", "/products/edit",
		"/products/show", "/products/delete" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO prdDAO;
	private CategoryDAO cateDAO;

	public ProductServlet() {
		super();
		this.prdDAO = new ProductDAO();
		this.cateDAO = new CategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

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
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String uri = request.getRequestURI();
		if (uri.contains("store")) {
			this.store(request, response);
		} else if (uri.contains("update")) {
			this.update(request, response);
		}
	}

	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> dsPrd = this.prdDAO.getAll();

		request.setAttribute("khoangTrang", " ");
		request.setAttribute("dsPrd", dsPrd);
		request.setAttribute("view", "/views/admin/products/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

	}

	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> dsCate = this.cateDAO.getAll();

		request.setAttribute("dsCate", dsCate);
		request.setAttribute("view", "/views/admin/products/create.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int id = Integer.parseInt(request.getParameter("category_id"));
		System.out.println("category id : " + id);
		Category cate = this.cateDAO.findByID(id);

		Product entity = new Product();
		try {
			BeanUtils.populate(entity, request.getParameterMap());

			File file = new File(request.getServletContext().getRealPath("/images"));
			if (!file.exists()) {
				file.mkdirs();
			}
			Part part = request.getPart("img");
			File fileImg = new File(file, part.getSubmittedFileName());
			part.write(fileImg.getAbsolutePath());

			entity.setCategory(cate);
			entity.setImg(fileImg.getName());

			this.prdDAO.create(entity);

			session.setAttribute("message", "Th??m th??nh c??ng");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/products/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Th??m th???t b???i");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/products/create");
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int idCate = Integer.parseInt(request.getParameter("category_id"));
		Category cate = this.cateDAO.findByID(idCate);

		int idPrd = Integer.parseInt(request.getParameter("id"));
//		Product prd = this.prdDAO.findByID(idPrd);

//		String checkImg = request.getParameter("img");
//		System.out.println("1 Check img g???c getParameter: " + checkImg);
//		System.out.println("2 Check c??: prd.getImg() > " + prd.getImg());

		try {
			Product entity = new Product();
			BeanUtils.populate(entity, request.getParameterMap());

//			System.out.println("3 Check img m???i: entity.getImg() >" + entity.getImg());

			File file = new File(request.getServletContext().getRealPath("/images"));
			Part part = request.getPart("img");
			File fileImg = new File(file, part.getSubmittedFileName());
			part.write(fileImg.getAbsolutePath());
//			System.out.println("4 Check m???i: fileImg.getName() > " + fileImg.getName());
//			System.out.println("5 Check m???i: part > " + part.getSubmittedFileName());

			entity.setId(idPrd);
			entity.setCategory(cate);
			entity.setImg(fileImg.getName());

			this.prdDAO.update(entity);
			session.setAttribute("message", "S???a th??nh c??ng");
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/products/index");
		} catch (Exception e) {
			session.setAttribute("erro", "S???a th???t b???i");
			e.printStackTrace();
			response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/products/edit?id=" + idPrd);
		}
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> dsCate = this.cateDAO.getAll();
		request.setAttribute("dsCate", dsCate);

		int idSP = Integer.parseInt(request.getParameter("id"));
		Product prd = this.prdDAO.findByID(idSP);
		request.setAttribute("prd", prd);
		request.setAttribute("view", "/views/admin/products/edit.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		Product prd = this.prdDAO.findByID(id);

		try {
			this.prdDAO.delete(prd);
			session.setAttribute("message", "Xo?? th??nh c??ng");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("erro", "Xo?? th???t b???i");
		}
		response.sendRedirect("/HiennvPH13697_SOF3011_Assignment/products/index");
	}

	public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
