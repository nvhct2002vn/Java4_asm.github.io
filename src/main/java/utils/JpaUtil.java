package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	public static EntityManagerFactory getFactory() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("HiennvPH13697_SOF3011_Assignment");
		return factory;
	}

	public static EntityManager getEntityManager() {
		EntityManager em = JpaUtil.getFactory().createEntityManager();
		return em;
	}
}
