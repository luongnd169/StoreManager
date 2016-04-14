package controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import dao.ItemDAO;
import model.Item;

public class ComboBoxModel {

	public DefaultComboBoxModel getList(String name) {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		List<Item> listItem = ItemDAO.getItem("FROM Item WHERE name LIKE '" + name + "%'");
		for (Item i : listItem) {
			model.addElement(i.getName());
		}
		return model;
	}

}
