package de.signaliduna.alexa.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import java.util.List;

/**
 * Data Acces Object (DAO) for CRUD operations on {@link Greeting} entities.
 */
@ApplicationScoped
public class GreetingDAO  {

	@Inject
	private SessionFactory sessionFactory;

	public String getGreeting() {
		Query query = currentSession().getNamedNativeQuery("somename")
				.setParameter("greeting", "Hello from the database!");
		List<String> results = query.getResultList();

		return results.get(0);
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
}
