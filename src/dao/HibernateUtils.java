package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import lib.IPathStorage;

public class HibernateUtils {

	public HibernateUtils() {

	}

	public SessionFactory getSessionFactory() {
		try {
			AnnotationConfiguration config = new AnnotationConfiguration();
			config.configure(IPathStorage.HIBERNATE_CONFIG);
			return config.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Session getSession() {
		try {
			return getSessionFactory().getCurrentSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new AnnotationConfiguration().configure().buildSessionFactory();

		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory1() {
		return sessionFactory;
	}

}
