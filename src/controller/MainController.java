package controller;

import java.util.ArrayList;
import java.util.List;

import dao.ItemDAO;
import gui.Main;
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

	public static void main(String[] args) {
		List<Item> list = new ArrayList<Item>();
		// System.out.println(addItemToList(list).size() + "abc");
	}

}
