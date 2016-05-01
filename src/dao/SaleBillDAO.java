package dao;

import java.util.List;

import org.hibernate.Session;

import model.SaleBill;

public class SaleBillDAO {
	private static HibernateUtils utils = new HibernateUtils();

	public static SaleBill getSaleBill(int id) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			SaleBill saleBill = (SaleBill) session.get(SaleBill.class, id);
			session.beginTransaction().commit();
			return saleBill;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<SaleBill> getSaleBills() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<SaleBill> list = session.createQuery("FROM SaleBill").list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<SaleBill> getSaleBill(String query) {
		System.out.println(query);
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<SaleBill> list = session.createQuery(query).list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(SaleBill SaleBill) {
		process(SaleBill, "insert");
	}

	public static void update(SaleBill SaleBill) {
		process(SaleBill, "update");
	}

	public static void delete(SaleBill SaleBill) {
		process(SaleBill, "delete");
	}

	private static void process(SaleBill SaleBill, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(SaleBill);
				break;
			case "update":
				session.update(SaleBill);
				break;
			case "delete":
				session.delete(SaleBill);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
