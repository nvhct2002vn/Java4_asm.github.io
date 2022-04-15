package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Cart;
import entities.Cartdetail;
import utils.JpaUtil;

public class CartDAO {
	private EntityManager em;

	public CartDAO() {
		this.em = JpaUtil.getEntityManager();
	}

	public List<Cart> getAll() {
		String jqpl = "SELECT c FROM Cart c";
		TypedQuery<Cart> query = this.em.createQuery(jqpl, Cart.class);
		List<Cart> resault = query.getResultList();
		return resault;
	}

	public Cart findByID(int id) {
		return this.em.find(Cart.class, id);
	}

	public Cart create(Cart entity) throws Exception {
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

	public Cart update(Cart entity) throws Exception {
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

	public Cart delete(Cart entity) throws Exception {
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
