package dao;

import java.util.List;

import org.hibernate.Session;

import model.BillDetail;

public class BillDetailDAO {
	private static HibernateUtils utils = new HibernateUtils();

	public static BillDetail getBillDetail(int id) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			BillDetail billDetail = (BillDetail) session.get(BillDetail.class, id);
			session.beginTransaction().commit();
			return billDetail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<BillDetail> getBillDetails() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<BillDetail> list = session.createQuery("FROM BillDetail").list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<BillDetail> getBillDetail(String query) {
		System.out.println(query);
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<BillDetail> list = session.createQuery(query).list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(BillDetail BillDetail) {
		process(BillDetail, "insert");
	}

	public static void update(BillDetail BillDetail) {
		process(BillDetail, "update");
	}

	public static void delete(BillDetail BillDetail) {
		process(BillDetail, "delete");
	}

	private static void process(BillDetail BillDetail, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(BillDetail);
				break;
			case "update":
				session.update(BillDetail);
				break;
			case "delete":
				session.delete(BillDetail);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
