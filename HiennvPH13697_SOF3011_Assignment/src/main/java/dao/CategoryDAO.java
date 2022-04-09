package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Category;
import utils.JpaUtil;

public class CategoryDAO {

	private EntityManager em;

	public CategoryDAO() {
		this.em = JpaUtil.getEntityManager();
	}

	public List<Category> getAll() {
		String jqpl = "SELECT c FROM Category c";
		TypedQuery<Category> query = this.em.createQuery(jqpl, Category.class);
		List<Category> resault = query.getResultList();
		return resault;
	}

	public Category findByID(int id) {
		return this.em.find(Category.class, id);
	}

	public Category create(Category entity) throws Exception {
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

	public Category update(Category entity) throws Exception {
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

	public Category delete(Category entity) throws Exception {
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
