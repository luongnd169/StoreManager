package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import dao.ItemDAO;
import dao.ItemDetailDAO;
import dao.SaleBillDAO;
import dao.SaleBillDetailDAO;
import gui.Main;
import model.Customer;
import model.Item;
import model.ItemDetail;
import model.SaleBill;
import model.SaleBillDetail;

public class MainController {

	Main main;

	public MainController() {

	}

	public void addItemToList(List<Item> listItem, String name) {
		System.out.println("Name = " + ItemDAO.getItem("FROM Item where name = '" + name + "'").get(0).getName());
		listItem.add(ItemDAO.getItem("FROM Item where name = '" + name + "'").get(0));
	}

	public void saveSaleBill(List<Item> listItem, Customer c, String imei) {
		Integer nextBillNo = 1;
		if (SaleBillDAO.getSaleBills().size() != 0) {
			nextBillNo = SaleBillDAO.getSaleBills().get(SaleBillDAO.getSaleBills().size() - 1).getBillNo() + 1;
		}
		List<Item> list = ItemDAO.getItemes();
		List<Item> temp = new ArrayList<>();
		for (Item i : listItem) {
			temp.add(i);
		}
		SaleBill bill = new SaleBill();
		bill.setBillNo(nextBillNo);
		int totalPrice = 0;
		for (Item i : listItem) {
			totalPrice += Integer.parseInt(i.getPrice()) * i.getQuantity();
		}
		bill.setDate(new Date());
		bill.setTotalPrice(totalPrice + "");
		bill.setCustomerPhone(c.getPhone());
		SaleBillDAO.insert(bill);
		SaleBillDetail detail = new SaleBillDetail();
		detail.setBillNo(nextBillNo);
		detail.setDate(bill.getDate());
		for (Item i : listItem) {
			detail.setName(i.getName());
			detail.setPrice(i.getPrice());
			detail.setQuantity(i.getQuantity());
			SaleBillDetailDAO.insert(detail);
		}
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < listItem.size(); j++) {
				if (list.get(i).getItemId().equals(listItem.get(j).getItemId())) {
					list.get(i).setQuantity(list.get(i).getQuantity() - listItem.get(j).getQuantity());

				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < temp.size(); j++) {
				if (list.get(i).getItemId().equals(temp.get(j).getItemId())) {
					temp.get(j).setQuantity(list.get(i).getQuantity());
					temp.get(j).setPrice(list.get(i).getPrice());
				}
			}
		}

		for (Item i : temp) {
			ItemDAO.update(i);
		}
		ItemDetail id = ItemDetailDAO.getItemDetail("From ItemDetail where imei = '" + imei + "'").get(0);
		id.setStatus(false);
		ItemDetailDAO.update(id);

	}

	public List<Item> searchItemByName(List<Item> listItem, String name) {
		List<Item> temp = new ArrayList<>();
		for (Item i : listItem) {
			if (containsOf(i.getName().toLowerCase().trim(), name.toLowerCase().trim())) {
				temp.add(i);
			}
		}
		return temp;
	}

	public List<Item> searchItemByImei(String imei) {
		List<Item> temp = new ArrayList<>();
		if (ItemDetailDAO.getItemDetail("From ItemDetail where imei ='" + imei + "'") != null) {
			ItemDetail id = ItemDetailDAO.getItemDetail("From ItemDetail where imei ='" + imei + "'").get(0);
			Item i = ItemDAO.getItem(id.getItemId());
			temp.add(i);
		}
		return temp;
	}

	public boolean containsOf(String s1, String s2) {
		boolean isContain = false;
		StringTokenizer st = new StringTokenizer(s2, " ");
		while (st.hasMoreElements()) {
			String nextToken = st.nextToken();
			if (s1.contains(nextToken)) {
				s1 = s1.replaceFirst(nextToken, "");
				isContain = true;

			} else {
				isContain = false;
				return false;
			}
		}

		return isContain;
	}

	public void replace(String s, String s1, String s2) {
		s.replace(s1, s2);
	}

//	private boolean imeiContaint(String s1, String s2) {
//		boolean isContain = false;
//		String[] st1 = s1.split("");
//		String[] st2 = s2.split("");
//		for (int i = 0; i < st2.length; i++) {
//			if (st2[i].equals(st1[i])) {
//				isContain = true;
//			} else {
//				isContain = false;
//				return false;
//			}
//		}
//
//		return isContain;
//	}

}
