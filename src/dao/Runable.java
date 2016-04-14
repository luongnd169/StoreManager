package dao;

import java.util.Date;

import org.hibernate.Session;

import model.Bill;
import model.Item;

public class Runable {

	public static void main(String[] args) {
		HibernateUtils utils = new HibernateUtils();
		Session session = utils.getSession();
		session.beginTransaction();

		Item i = new Item();
		i.setModel("ipa");
		i.setName("iPad Air");
		i.setType("Tablet");
		i.setPrice("10000000");
		i.setQuantity(5);

		session.save(i);

		System.out.println("Done");

		Bill bill = new Bill();
		bill.setBillNo(00001);
		bill.setType(true);
		bill.setDate(new Date());
		bill.setTotalPrice("71000000");
		bill.setItem(i);
		i.getBill().add(bill);

		Bill bill1 = new Bill();
		bill1.setBillNo(00001);
		bill1.setType(true);
		bill1.setDate(new Date());
		bill1.setTotalPrice("71000000");
		bill1.setItem(i);
		i.getBill().add(bill1);

		session.save(bill);
		session.save(bill1);

		session.getTransaction().commit();
		System.out.println("done");

	}

}
