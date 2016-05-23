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
			Bill saleBill = (Bill) session.get(Bill.class, id);
			session.beginTransaction().commit();
			return saleBill;
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

	public static String getNextBill(String type) {
		String nextBill = "";
		List<Bill> listBill = getBill("From Bill where billNo like '" + type + "%'");
		if (listBill.isEmpty()) {
			nextBill = type + "00001";
		} else {
			String lastBill = listBill.get(listBill.size()).getBillNo() + "";
			nextBill = lastBill.substring(2);
			if (Integer.parseInt(nextBill) < 10) {
				nextBill = type + "0000" + nextBill;
			} else if (Integer.parseInt(nextBill) < 100) {
				nextBill = type + "000" + nextBill;
			} else if (Integer.parseInt(nextBill) < 1000) {
				nextBill = type + "00" + nextBill;
			} else if (Integer.parseInt(nextBill) < 10000) {
				nextBill = type + "0" + nextBill;
			} else if (Integer.parseInt(nextBill) < 100000) {
				nextBill = type + nextBill;
			}
		}
		return nextBill;
	}

	public static void insert(Bill bill) {
		process(bill, "insert");
	}

	public static void update(Bill bill) {
		process(bill, "update");
	}

	public static void delete(Bill bill) {
		process(bill, "delete");
	}

	private static void process(Bill bill, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(bill);
				break;
			case "update":
				session.update(bill);
				break;
			case "delete":
				session.delete(bill);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
