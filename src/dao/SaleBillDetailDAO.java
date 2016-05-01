package dao;

import java.util.List;

import org.hibernate.Session;

import model.SaleBillDetail;

public class SaleBillDetailDAO {
	private static HibernateUtils utils = new HibernateUtils();

	public static SaleBillDetail getSaleBillDetail(int id) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			SaleBillDetail saleBillDetail = (SaleBillDetail) session.get(SaleBillDetail.class, id);
			session.beginTransaction().commit();
			return saleBillDetail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<SaleBillDetail> getSaleBillDetails() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<SaleBillDetail> list = session.createQuery("FROM SaleBillDetail").list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<SaleBillDetail> getBillDetail(String query) {
		System.out.println(query);
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<SaleBillDetail> list = session.createQuery(query).list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(SaleBillDetail SaleBillDetail) {
		process(SaleBillDetail, "insert");
	}

	public static void update(SaleBillDetail SaleBillDetail) {
		process(SaleBillDetail, "update");
	}

	public static void delete(SaleBillDetail SaleBillDetail) {
		process(SaleBillDetail, "delete");
	}

	private static void process(SaleBillDetail SaleBillDetail, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(SaleBillDetail);
				break;
			case "update":
				session.update(SaleBillDetail);
				break;
			case "delete":
				session.delete(SaleBillDetail);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
