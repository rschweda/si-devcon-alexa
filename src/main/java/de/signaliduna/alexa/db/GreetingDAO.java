package de.signaliduna.alexa.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;


public class GreetingDAO extends AbstractDAO<Greeting> {

	public GreetingDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public String getGreeting() {
		Query query = currentSession().getNamedNativeQuery("somename")
				.setParameter("greeting", "Hello Hibernate");
		List<String> results = query.getResultList();

		return results.get(0);
	}

}
