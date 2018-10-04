package de.signaliduna.alexa.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class GreetingDAO  {

	@Inject
	private SessionFactory sessionFactory;

	public String getGreeting() {
		Query query = currentSession().getNamedNativeQuery("somename")
				.setParameter("greeting", "Hello Hibernate");
		List<String> results = query.getResultList();

		return results.get(0);
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
}
