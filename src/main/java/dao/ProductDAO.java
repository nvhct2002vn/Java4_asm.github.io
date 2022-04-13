package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Product;
import utils.JpaUtil;

public class ProductDAO {

	private EntityManager em;

	public ProductDAO() {
		this.em = JpaUtil.getEntityManager();
	}

	public List<Product> getAll() {
		String jqpl = "SELECT p FROM Product p";
		TypedQuery<Product> query = this.em.createQuery(jqpl, Product.class);
		List<Product> resault = query.getResultList();
		return resault;
	}

	public Product findByID(int id) {
		return this.em.find(Product.class, id);
	}

	public List<Product> getAllByIDCart(int category) {
		String jpql = "SELECT c FROM Product c Where category_id= :category";

		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class);
		query.setParameter("category", category);
		List<Product> resault = query.getResultList();
		return resault;
	}

	public Product create(Product entity) throws Exception {
		try {
			this.em.getTransaction().begin();

			this.em.persist(entity);

			this.em.getTransaction().commit();

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}

	public Product update(Product entity) throws Exception {
		try {
			this.em.getTransaction().begin();

			this.em.merge(entity);

			this.em.getTransaction().commit();

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}

	public Product delete(Product entity) throws Exception {
		try {
			this.em.getTransaction().begin();

			this.em.remove(entity);

			this.em.getTransaction().commit();

			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}

}
