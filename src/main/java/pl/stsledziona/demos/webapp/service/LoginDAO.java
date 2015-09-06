package pl.stsledziona.demos.webapp.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.stsledziona.demos.webapp.domain.User;

@Stateless
public class LoginDAO {

	@PersistenceContext
	EntityManager em;

	public boolean validate(String username, String password) {
		try {
			User u = em.createNamedQuery("validateQuery", User.class).setParameter("username", username)
					.setParameter("password", password).getSingleResult();
			if (u != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
