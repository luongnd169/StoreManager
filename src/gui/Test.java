package gui;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.TableModel;
import dao.ItemDAO;
import model.Item;

public class Test {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	List<Item> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		System.out.println(ItemDAO.getItemes().size());
		
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Item i1 = new Item();
		i1.setName("iPhone 4 16GB Gray");
		Item i2 = new Item();
		i2.setName("iPhone 4 32GB Gray");
		Item i3 = new Item();
		i3.setName("iPhone 5 16GB Light");
		Item i4 = new Item();
		i4.setName("iPhone 5s 16GB Gray");
		Item i5 = new Item();
		i5.setName("iPhone 6s 16GB Light");
		list = new ArrayList<>();
		list.add(i1);
		list.add(i2);
		list.add(i3);
		list.add(i4);
		list.add(i5);
		textField = new JTextField();
		textField.setBounds(126, 89, 261, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = textField.getText().toString().trim().toLowerCase();
				List<Item> temp = new ArrayList<>();
//				for (Item i : list) {
//					String name = i.getName().toLowerCase();
//					System.out.println(name);
//					if (text.contains(name)) {
//						System.out.println("1");
//						temp.add(i);
//					}
//				}
				for(int i = 0; i < list.size(); i++){
					String name = list.get(i).getName().toLowerCase();
					if(name.contains(text)){
						temp.add(list.get(i));
					}
				}
				table.setModel(new TableModel(temp));
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 249, 406, 195);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new TableModel(list));
		scrollPane.setViewportView(table);
	}
}
