package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.BillDAO;
import dao.BillDetailDAO;
import dao.ItemDAO;
import gui.Main;
import model.Bill;
import model.BillDetail;
import model.Item;

public class MainController {

	Main main = new Main();

	public MainController() {

	}

	public List<Item> addItemToList(List<Item> listItem, String name) {
		System.out.println("Item name: " + name);
		listItem = ItemDAO.getItem("FROM Item where name = '" + name + "'");
		System.out.println("Size: " + listItem.size());
		if (listItem.isEmpty()) {
			return null;
		}
		return listItem;
	}

	public void saveSaleBill(List<Item> listItem) {
		Integer nextBillNo = 1;
		if (BillDAO.getBills().size() != 0) {
			nextBillNo = BillDAO.getBills().get(BillDAO.getBills().size() - 1).getBillNo() + 1;
		}
		List<Item> list = ItemDAO.getItemes();
		for (Item i : list) {
			for (Item i1 : listItem) {
				if (i1.getItemId() == i.getItemId()) {
					i.setQuantity(i.getQuantity() - i1.getQuantity());
				}
			}
		}
		for (int i = 0; i < listItem.size(); i++) {
			ItemDAO.update(list.get(i));
		}

		Bill bill = new Bill();
		bill.setBillNo(nextBillNo);
		bill.setType("Xuất");
		int totalPrice = 0;
		for (Item i : listItem) {
			totalPrice += Integer.parseInt(i.getPrice()) * i.getQuantity();
		}
		bill.setDate(new Date());
		bill.setTotalPrice(totalPrice + "");
		System.out.println(bill.toString());
		BillDAO.insert(bill);
		BillDetail detail = new BillDetail();
		detail.setBillNo(nextBillNo);
		detail.setBillType("Bán");
		detail.setDate(new Date());
		for (Item i : listItem) {
			detail.setName(i.getName());
			detail.setPrice(i.getPrice());
			BillDetailDAO.insert(detail);
		}

	}

	public static void main(String[] args) {
		List<Item> list = new ArrayList<Item>();
		// System.out.println(addItemToList(list).size() + "abc");
	}

}
