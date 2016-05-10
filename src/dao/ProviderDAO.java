package dao;

import java.util.List;

import org.hibernate.Session;

import model.Provider;

public class ProviderDAO {

	private static HibernateUtils utils = new HibernateUtils();

	public static Provider getProvider(int id) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			Provider provider = (Provider) session.get(Provider.class, id);
			session.beginTransaction().commit();
			return provider;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Provider> getProviders() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Provider> list = session.createQuery("FROM Provider").list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Provider> getProvider(String query) {
		System.out.println(query);
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Provider> list = session.createQuery(query).list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(Provider provider) {
		process(provider, "insert");
	}

	public static void update(Provider provider) {
		process(provider, "update");
	}

	public static void delete(Provider provider) {
		process(provider, "delete");
	}

	private static void process(Provider provider, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(provider);
				break;
			case "update":
				session.update(provider);
				break;
			case "delete":
				session.delete(provider);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
