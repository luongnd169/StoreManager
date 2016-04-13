package dao;

import java.util.List;

import org.hibernate.Session;

import model.Item;

public class ItemDAO {
	private static HibernateUtils utils = new HibernateUtils();

	public static Item getItem(int id) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			Item Item = (Item) session.get(Item.class, id);
			session.beginTransaction().commit();
			return Item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Item> getItemes() {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Item> list = session.createQuery("FROM Item").list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Item> getItem(String query) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Item> list = session.createQuery(query).list();
			session.beginTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(Item Item) {
		process(Item, "insert");
	}

	public static void update(Item Item) {
		process(Item, "update");
	}

	public static void delete(Item Item) {
		process(Item, "delete");
	}

	private static void process(Item Item, String mode) {
		try {
			Session session = utils.getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(Item);
				break;
			case "update":
				session.update(Item);
				break;
			case "delete":
				session.delete(Item);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
