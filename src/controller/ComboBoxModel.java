package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import dao.ItemDAO;
import dao.ItemDetailDAO;
import model.Item;
import model.ItemDetail;

public class ComboBoxModel {
	
	MainController main = new MainController();

	public DefaultComboBoxModel getList(String name) {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		List<Item> listItem = ItemDAO.getItemes();
		List<Item> searchItem = main.searchItem(listItem, name);
		for (Item i : searchItem) {
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
	
	public DefaultComboBoxModel emptyList(){
		DefaultComboBoxModel model = new DefaultComboBoxModel();
//		List<Item> listItem = new ArrayList<>();
		return model;
	}

}
