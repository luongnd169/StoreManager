package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

import controller.ComboBoxModel;
import controller.MainController;
import controller.TableModel;
import dao.ItemDAO;
import lib.Convert;
import model.Item;

public class Main {

	private JFrame frame;
	private JTextField txtSearch;
	private JTable table;
	private JComboBox comboBox_1;
	ComboBoxModel model = new ComboBoxModel();
	MainController controller;
	private List<Item> listItem = new ArrayList<Item>();
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	JComboBox comboBox_2;

	public JComboBox getComboBox_1() {
		return comboBox_1;
	}

	public void setComboBox_1(JComboBox comboBox_1) {
		this.comboBox_1 = comboBox_1;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTable getTable_1() {
		return table_1;
	}

	public void setTable_1(JTable table_1) {
		this.table_1 = table_1;
	}

	/**
	 * Launch the application.
	 * 
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 */
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 0, 774, 560);
		panel.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Tồn kho", null, panel_1, null);
		panel_1.setBounds(10, 0, 774, 560);
		panel_1.setLayout(null);

		JLabel lblSortBy = new JLabel("Sắp xếp theo");
		lblSortBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSortBy.setBounds(10, 11, 100, 40);
		panel_1.add(lblSortBy);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(120, 23, 28, 20);
		panel_1.add(comboBox);

		JLabel lblSearch = new JLabel("Tìm kiếm");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearch.setBounds(360, 11, 100, 40);
		panel_1.add(lblSearch);

		txtSearch = new JTextField();
		txtSearch.setBounds(470, 17, 200, 32);
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		panel_1.add(txtSearch);
		txtSearch.setColumns(10);

		table = new JTable();
		table.setBounds(10, 100, 750, 421);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 100, 750, 421);
		try {
			table.setModel(new TableModel(ItemDAO.getItemes()) {

				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] { false, false, false, true, true };

				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		panel_1.add(scrollPane);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Nhập/Xuất", null, panel_2, null);
		panel_2.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 769, 532);
		panel_2.add(tabbedPane_1);

		JPanel panel_3 = new JPanel();

		tabbedPane_1.addTab("Xuất", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblName = new JLabel("Sản phẩm");
		lblName.setBounds(63, 18, 62, 17);
		panel_3.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1 = new JComboBox<>();
		comboBox_1.setBounds(169, 11, 240, 34);
		panel_3.add(comboBox_1);
		comboBox_1.setEditable(true);

		table_1 = new JTable();
		table_1.setBounds(10, 150, 745, 262);
		JScrollPane scrollPane1 = new JScrollPane(table_1);
		scrollPane1.setBounds(10, 186, 750, 262);
		table_1.setEditingColumn(3);
		panel_3.add(scrollPane1);

		JButton btnThm = new JButton("Thêm");
		btnThm.setBounds(419, 17, 72, 23);
		btnThm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller = new MainController();
				Item i = controller.addItemToList(listItem, comboBox_1.getEditor().getItem().toString()).get(0);
				i.setQuantity(Integer.parseInt(comboBox_2.getSelectedItem().toString()));
				i.setPrice(textField_1.getText().trim());
				listItem.add(i);
				for(Item item : listItem){
					item.setPrice(Convert.stringToNumber(textField_1.getText().trim()));
				}
				try {
					table_1.setModel(new TableModel(listItem) {
					});
					for(Item item : listItem){
						item.setPrice(Convert.numberToString(item.getPrice()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel_3.add(btnThm);

		// JRadioButton rdbtnThanhTonNgay = new JRadioButton("Thanh toán");
		// rdbtnThanhTonNgay.setBounds(582, 7, 109, 23);
		// panel_3.add(rdbtnThanhTonNgay);
		//
		// JRadioButton rdbtnCngN = new JRadioButton("Công nợ");
		// rdbtnCngN.setBounds(582, 29, 109, 23);
		// panel_3.add(rdbtnCngN);

		JButton btnLu = new JButton("Lưu");
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.saveSaleBill(listItem);
			}
		});
		btnLu.setBounds(602, 470, 89, 23);
		panel_3.add(btnLu);

		JLabel lblSLng = new JLabel("Số lượng\r\n");
		lblSLng.setHorizontalAlignment(SwingConstants.CENTER);
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSLng.setBounds(63, 61, 62, 17);
		panel_3.add(lblSLng);

		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(169, 61, 50, 20);
		panel_3.add(comboBox_2);

		JLabel lblGiNhp = new JLabel("Giá nhập");
		lblGiNhp.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiNhp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiNhp.setBounds(63, 115, 62, 17);
		panel_3.add(lblGiNhp);

		JLabel lblGiXut = new JLabel("Giá xuất");
		lblGiXut.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiXut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiXut.setBounds(297, 115, 62, 17);
		panel_3.add(lblGiXut);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(169, 115, 86, 20);
		panel_3.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(405, 115, 86, 20);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String name = comboBox_1.getEditor().getItem().toString();
				List<Item> listItem = ItemDAO.getItem("FROM Item where name = '" + name + "'");
				if (listItem.size() > 0 && !textField_1.getText().equals("")) {
					textField_2.setText(Convert.stringToNumber(String.valueOf(
							Integer.parseInt(textField_1.getText()) - Integer.parseInt(listItem.get(0).getPrice()))));
				}
			}
		});
		panel_3.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblLiNhun = new JLabel("Lợi nhuận");
		lblLiNhun.setHorizontalAlignment(SwingConstants.CENTER);
		lblLiNhun.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLiNhun.setBounds(549, 115, 62, 17);
		panel_3.add(lblLiNhun);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(655, 115, 86, 20);

		panel_3.add(textField_2);
		textField_2.setColumns(10);
		comboBox_1.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String price = "";
				int quantity = 0;
				int index = 0;
				String name = comboBox_1.getEditor().getItem().toString();
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					index = 0;
					index = comboBox_2.getItemCount();
					for (int i = 0; i < index; i++) {
						comboBox_2.removeItemAt(0);
					}
					List<Item> list = ItemDAO.getItem("FROM Item where name = '" + name + "'");
					if (list.size() > 0) {
						quantity = list.get(0).getQuantity();
						for (int i = 1; i <= quantity; i++) {
							comboBox_2.addItem(i);
						}
						price = list.get(0).getPrice();
						textField.setText(Convert.stringToNumber(price));
					}
				}
				if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90 || e.getKeyCode() >= 96 && e.getKeyCode() <= 105
						|| e.getKeyCode() == 8) {
					comboBox_1.setModel(model.getList(name));
					if (comboBox_1.getItemCount() > 0) {
						comboBox_1.showPopup();
						if (e.getKeyCode() != 8) {
							((JTextComponent) comboBox_1.getEditor().getEditorComponent()).select(name.length(),
									comboBox_1.getEditor().getItem().toString().length());

						} else {
							comboBox_1.getEditor().setItem(name);

						}
					} else {
						comboBox_1.addItem(name);
					}

				}

				super.keyReleased(e);
			}

		});

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Thống kê", null, panel_4, null);
	}

	private boolean compare(String s) {
		Object[] list = comboBox_1.getComponents();
		boolean flag = false;
		for (Object object : list) {
			if (s.equals(object)) {
				flag = true;
				break;
			}

		}
		return flag;
	}

	public void search(String name) {

	}
}
