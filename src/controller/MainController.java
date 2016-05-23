package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import dao.ItemDAO;
import dao.ItemDetailDAO;
import dao.BillDAO;
import dao.BillDetailDAO;
import gui.Main;
import lib.Convert;
import model.Customer;
import model.Item;
import model.ItemDetail;
import model.Bill;
import model.BillDetail;

public class MainController {

	Main main;

	public MainController() {

	}

	public void addItemToList(List<Item> listItem, String name) {
		System.out.println("Name = " + ItemDAO.getItem("FROM Item where name = '" + name + "'").get(0).getName());
		listItem.add(ItemDAO.getItem("FROM Item where name = '" + name + "'").get(0));
	}

	public void saveBill(List<Item> listItem, List<ItemDetail> listItemDetail, Customer c, String type) {
		String nextBill = BillDAO.getNextBill(type);
		List<Item> list = ItemDAO.getItemes();
		List<Item> temp = new ArrayList<>();
		for (Item i : listItem) {
			temp.add(i);
		}
		Bill bill = new Bill();
		bill.setBillNo(nextBill);
		int totalPrice = 0;
		for (Item i : listItem) {
			totalPrice += Integer.parseInt(i.getPrice()) * i.getQuantity();
		}
		bill.setDate(new Date());
		bill.setTotalPrice(totalPrice + "");
		bill.setCustomerPhone(c.getPhone());
		BillDAO.insert(bill);
		BillDetail detail = new BillDetail();
		detail.setBillNo(nextBill);
		detail.setDate(bill.getDate());
		for (Item i : listItem) {
			detail.setName(i.getName());
			detail.setPrice(i.getPrice());
			detail.setQuantity(i.getQuantity());
			BillDetailDAO.insert(detail);
		}
		// for (int i = 0; i < list.size(); i++) {
		// for (int j = 0; j < listItem.size(); j++) {
		// if (list.get(i).getItemId().equals(listItem.get(j).getItemId())) {
		// list.get(i).setQuantity(list.get(i).getQuantity() -
		// listItem.get(j).getQuantity());
		//
		// }
		// }
		// }
		for (Item i1 : list) {
			for (Item i2 : listItem) {
				if (i1.getItemId().equals(i2.getItemId())) {
					if (type.equals("X")) {
						i1.setQuantity(i1.getQuantity() - i2.getQuantity());
					} else {
						i1.setQuantity(i1.getQuantity() + i2.getQuantity());
					}
				}
			}
		}
		//

		// for (int i = 0; i < list.size(); i++) {
		// for (int j = 0; j < temp.size(); j++) {
		// if (list.get(i).getItemId().equals(temp.get(j).getItemId())) {
		// temp.get(j).setQuantity(list.get(i).getQuantity());
		// temp.get(j).setPrice(list.get(i).getPrice());
		// }
		// }
		// }

		for (Item i1 : list) {
			for (Item i2 : temp) {
				if (i1.getItemId().equals(i2.getItemId())) {
					i2.setQuantity(i1.getQuantity());
				}
			}
		}
		//
		for (ItemDetail id : listItemDetail) {
			id.setCustomer(c.getCustomerId());
			id.setExportDate(Convert.formatDateSQL(new Date()));
			id.setStatus(false);
			ItemDetailDAO.update(id);
		}
		// sửa giá bình quân
		for (Item i : temp) {
			int updatePrice = 0;
			List<ItemDetail> listDetail = ItemDetailDAO
					.getItemDetail("From ItemDetail where itemId = " + i.getItemId() + " and status = 1");
			if (!listDetail.isEmpty()) {
				for (ItemDetail id : listDetail) {
					updatePrice += Integer.parseInt(id.getImportPrice());
				}
			}
			i.setPrice(String.valueOf(updatePrice / listDetail.size()));
		}
		for (Item i : temp) {
			ItemDAO.update(i);
		}

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

}
