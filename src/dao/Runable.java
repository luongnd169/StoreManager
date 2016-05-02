package dao;

import java.util.List;

import model.Item;

public class Runable {

	public static void main(String[] args) {
		List<Item> list = ItemDAO.getItemes();
		int price = 0;
		for (Item i : list) {
			price += Integer.parseInt(i.getPrice()) * i.getQuantity();
		}
		System.out.println(price);
	}

}
