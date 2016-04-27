package controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import dao.ItemDAO;
import dao.ItemDetailDAO;
import model.Item;
import model.ItemDetail;

public class ComboBoxModel {

	public DefaultComboBoxModel getList(String name) {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		List<Item> listItem = ItemDAO.getItem("FROM Item WHERE name LIKE '" + name + "%'");
		for (Item i : listItem) {
			model.addElement(i.getName());
		}
		return model;
	}
	
	public DefaultComboBoxModel getListByImei(String imei){
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		List<ItemDetail> listItemDetail = ItemDetailDAO.getItemDetail("FROM ItemDetail WHERE imei LIKE '" + imei + "%'");
		for (ItemDetail i : listItemDetail) {
			model.addElement(i.getImei());
		}
		return model;
	}

}
