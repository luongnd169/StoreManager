package controller;

import java.util.Date;
import java.util.List;

import dao.BillDAO;
import dao.BillDetailDAO;
import dao.ItemDAO;
import dao.ItemDetailDAO;
import gui.Main;
import model.Bill;
import model.BillDetail;
import model.Item;
import model.ItemDetail;

public class MainController {

	Main main;

	public MainController() {

	}

	public void addItemToList(List<Item> listItem, String name) {
		System.out.println("Name = " + ItemDAO.getItem("FROM Item where name = '" + name + "'").get(0).getName());
		listItem.add(ItemDAO.getItem("FROM Item where name = '" + name + "'").get(0));
	}

	public void saveSaleBill(List<Item> listItem) {
		Integer nextBillNo = 1;
		if (BillDAO.getBills().size() != 0) {
			nextBillNo = BillDAO.getBills().get(BillDAO.getBills().size() - 1).getBillNo() + 1;
		}
		List<Item> list = ItemDAO.getItemes();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < listItem.size(); j++) {
				if (list.get(i).getItemId().equals(listItem.get(j).getItemId())) {
					list.get(i).setQuantity(list.get(i).getQuantity() - listItem.get(j).getQuantity());
				}
			}
		}
		for (Item i : list) {
			ItemDAO.update(i);
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
		detail.setBillType("Xuất");
		detail.setDate(new Date());
		for (Item i : listItem) {
			detail.setName(i.getName());
			detail.setPrice(i.getPrice());
			detail.setQuantity(i.getQuantity());
			BillDetailDAO.insert(detail);
		}

	}

	public void showItemSelected(int selectedRow) {
		main  = new Main();
		System.out.println(selectedRow);
		Item item = ItemDAO.getItemes().get(selectedRow);
		ItemDetail itemDetail = ItemDetailDAO.getItemDetail(item.getItemId());
		main.getTxtMaSP().setText(item.getItemId() + "");
		main.getTxtTenSP().setText(item.getName());
		main.getTxtChungLoai().setText(item.getType());
		main.getTxtSoLng().setText(item.getQuantity() + "");
		main.getTxtGia().setText(item.getPrice());
		main.getTxtMauSac().setText(itemDetail.getColor());
		main.getTxtKhac().setText(itemDetail.getImei());
	}

}
