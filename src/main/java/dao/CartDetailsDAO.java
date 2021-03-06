package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Cartdetail;
import utils.JpaUtil;

public class CartDetailsDAO {
	private EntityManager em;

	public CartDetailsDAO() {
		this.em = JpaUtil.getEntityManager();
	}

	public List<Cartdetail> getAll() {
		String jqpl = "SELECT c FROM Cartdetail c";
		TypedQuery<Cartdetail> query = this.em.createQuery(jqpl, Cartdetail.class);
		List<Cartdetail> resault = query.getResultList();
		return resault;
	}

	public List<Cartdetail> getAllByIDCart(int cart) {
		String jpql = "SELECT c FROM Cartdetail c Where cart_id= :cart";

		TypedQuery<Cartdetail> query = this.em.createQuery(jpql, Cartdetail.class);
		query.setParameter("cart", cart);
		List<Cartdetail> resault = query.getResultList();
		return resault;
	}

	public Cartdetail findByID(int id) {
		return this.em.find(Cartdetail.class, id);
	}

	public Cartdetail create(Cartdetail entity) throws Exception {
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

	public Cartdetail update(Cartdetail entity) throws Exception {
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

	public Cartdetail delete(Cartdetail entity) throws Exception {
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

	public void deleteAll(int idCart) {
		String sql = "DELETE FROM Cartdetail WHERE cart_id = :id ";
		TypedQuery<Cartdetail> query = this.em.createNamedQuery(sql, Cartdetail.class);
		query.setParameter("cart_id", idCart);
		query.executeUpdate();
	}
}
