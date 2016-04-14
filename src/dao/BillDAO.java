package dao;

import java.util.List;

import org.hibernate.Session;

import model.Bill;

public class BillDAO {
	private static HibernateUtils utils = new HibernateUtils();

	public static Bill getBill(int id) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			Bill bill = (Bill) session.get(Bill.class, id);
			session.beginTransaction().commit();
			return bill;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Bill> getBills() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Bill> list = session.createQuery("FROM Bill").list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Bill> getBill(String query) {
		System.out.println(query);
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Bill> list = session.createQuery(query).list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(Bill Bill) {
		process(Bill, "insert");
	}

	public static void update(Bill Bill) {
		process(Bill, "update");
	}

	public static void delete(Bill Bill) {
		process(Bill, "delete");
	}

	private static void process(Bill Bill, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(Bill);
				break;
			case "update":
				session.update(Bill);
				break;
			case "delete":
				session.delete(Bill);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
