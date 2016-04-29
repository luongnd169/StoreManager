package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;

import controller.ComboBoxModel;
import controller.MainController;
import controller.TableModel;
import dao.ItemDAO;
import dao.ItemDetailDAO;
import lib.Convert;
import model.Item;
import model.ItemDetail;

public class Main {

	private JFrame frame;
	private JTable table;
	private JComboBox comboBoxTimSP;
	ComboBoxModel model = new ComboBoxModel();
	MainController controller;
	private List<Item> listItem = new ArrayList<Item>();
	private JTable table_1;
	private JTextField txtGiaNhap;
	private JTextField txtGiaXuat;
	private JTextField txtLoiNhuan;
	JComboBox comboBoxSoLuong;
	int index = 0;
	private JTextField txtTongTien;
	private JRadioButton rdbtnTenSP;
	private JRadioButton rdbtnSoImei;
	int warehouse;
	private JTextField txtSearch;
	private JTextField textField_1;
	private List<Item> listStorage = new ArrayList<>();

	public JComboBox getComboBox_1() {
		return comboBoxTimSP;
	}

	public void setComboBox_1(JComboBox comboBox_1) {
		this.comboBoxTimSP = comboBox_1;
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
		initData();
		controller = new MainController();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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

		JPanel panelTonKho = new JPanel();
		tabbedPane.addTab("Tồn kho", null, panelTonKho, null);
		panelTonKho.setBounds(10, 0, 774, 560);
		panelTonKho.setLayout(null);

		JLabel lblSearch = new JLabel("Tìm kiếm");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearch.setBounds(10, 35, 100, 30);
		panelTonKho.add(lblSearch);

		table = new JTable();
		table.setBounds(10, 100, 750, 375);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPaneTonKho = new JScrollPane(table);
		scrollPaneTonKho.setBounds(10, 119, 750, 375);
		try {

			table.setModel(new TableModel(listStorage) {
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] { false, false, false, true, true };

				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (true) {
			ListSelectionModel rowSM = table.getSelectionModel();
			rowSM.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (e.getValueIsAdjusting()) {
						return;
					}
					ListSelectionModel lsm = (ListSelectionModel) e.getSource();
					if (lsm.isSelectionEmpty()) {
						System.out.println("No rows are selected.");
					} else {
						Item item = ItemDAO.getItemes().get(lsm.getMinSelectionIndex());
						ItemDetail itemDetail = ItemDetailDAO.getItemDetail(item.getItemId());

					}
				}
			});
		} else {
			table.setRowSelectionAllowed(false);
		}
		panelTonKho.add(scrollPaneTonKho);

		rdbtnTenSP = new JRadioButton("Tên sản phẩm");
		rdbtnTenSP.setBounds(120, 7, 109, 23);
		panelTonKho.add(rdbtnTenSP);
		rdbtnTenSP.setSelected(true);
		ButtonModel btnModel = rdbtnTenSP.getModel();

		rdbtnSoImei = new JRadioButton("Số imei");
		rdbtnSoImei.setBounds(231, 7, 109, 23);
		panelTonKho.add(rdbtnSoImei);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSoImei);
		group.add(rdbtnTenSP);

		JLabel lblKho = new JLabel("Kho");
		lblKho.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKho.setBounds(455, 36, 50, 25);
		panelTonKho.add(lblKho);

		JComboBox comboKho = new JComboBox();
		comboKho.setBounds(506, 39, 100, 20);
		comboKho.addItem("Tất cả");
		comboKho.addItem("Điện thoại");
		comboKho.addItem("Máy tính bảng");
		comboKho.addItem("Linh kiện");
		comboKho.addItem("Phụ kiện");
		ItemListener itemListener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().equals("Tất cả")) {
					listStorage = ItemDAO.getItemes();
					table.setModel(new TableModel(convertListItem(listStorage)));
					warehouse = 0;
				} else if (e.getItem().equals("Điện thoại")) {
					listStorage = ItemDAO.getItem("From Item where type = 'Điện thoại'");
					table.setModel(new TableModel(convertListItem(listStorage)));
					warehouse = 1;
				} else if (e.getItem().equals("Máy tính bảng")) {
					listStorage = ItemDAO.getItem("From Item where type = 'Máy tính bảng'");
					table.setModel(new TableModel(convertListItem(listStorage)));
					warehouse = 2;
				} else if (e.getItem().equals("Linh kiện")) {
					listStorage = ItemDAO.getItem("From Item where type = 'Linh kiện'");
					table.setModel(new TableModel(convertListItem(listStorage)));
					warehouse = 3;
				} else if (e.getItem().equals("Phụ kiện")) {
					listStorage = ItemDAO.getItem("From Item where type = 'Phụ kiện'");
					table.setModel(new TableModel(convertListItem(listStorage)));
					warehouse = 4;
				}

			}
		};
		comboKho.addItemListener(itemListener);
		panelTonKho.add(comboKho);

		txtSearch = new JTextField();
		txtSearch.setBounds(120, 35, 200, 30);
		panelTonKho.add(txtSearch);
		txtSearch.setColumns(10);
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtSearch.getText().trim().equals("")) {
					table.setModel(new TableModel(listStorage));
				} else {
					table.setModel(new TableModel(controller.searchItem(listStorage, txtSearch.getText())));
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Tổng tiền");
		lblNewLabel.setBounds(561, 507, 50, 20);
		panelTonKho.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(628, 507, 132, 20);
		panelTonKho.add(textField_1);
		textField_1.setColumns(10);

		JPanel panelNhapXuat = new JPanel();
		tabbedPane.addTab("Nhập/Xuất", null, panelNhapXuat, null);
		panelNhapXuat.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 769, 532);
		panelNhapXuat.add(tabbedPane_1);

		JPanel panelXuat = new JPanel();

		tabbedPane_1.addTab("Xuất", null, panelXuat, null);
		panelXuat.setLayout(null);

		JLabel lblName = new JLabel("Sản phẩm");
		lblName.setBounds(63, 18, 62, 17);
		panelXuat.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxTimSP = new JComboBox<>();
		comboBoxTimSP.setBounds(169, 11, 240, 34);
		panelXuat.add(comboBoxTimSP);
		comboBoxTimSP.setEditable(true);

		table_1 = new JTable();
		table_1.setBounds(10, 150, 745, 262);
		JScrollPane scrollPaneXuat = new JScrollPane(table_1);
		scrollPaneXuat.setBounds(10, 157, 750, 262);
		table_1.setEditingColumn(3);
		panelXuat.add(scrollPaneXuat);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(419, 17, 72, 23);

		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Item i = ItemDAO
						.getItem("FROM Item where name = '" + comboBoxTimSP.getEditor().getItem().toString() + "'")
						.get(0);
				i.setQuantity(Integer.parseInt(comboBoxSoLuong.getSelectedItem().toString()));
				i.setPrice(txtGiaXuat.getText().trim());
				listItem.add(i);
				i.setPrice(Convert.stringToNumber(txtGiaXuat.getText().trim()));
				try {
					table_1.setModel(new TableModel(convertListItem(listItem)) {
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
				index++;
				int total = 0;
				for (Item item : listItem) {
					total += Integer.parseInt(Convert.numberToString(item.getPrice())) * item.getQuantity();
				}
				txtTongTien.setText(Convert.stringToNumber(String.valueOf(total)));
			}
		});
		panelXuat.add(btnThem);

		// JRadioButton rdbtnThanhTonNgay = new JRadioButton("Thanh toán");
		// rdbtnThanhTonNgay.setBounds(582, 7, 109, 23);
		// panel_3.add(rdbtnThanhTonNgay);
		//
		// JRadioButton rdbtnCngN = new JRadioButton("Công nợ");
		// rdbtnCngN.setBounds(582, 29, 109, 23);
		// panel_3.add(rdbtnCngN);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller = new MainController();
				for (Item i : listItem) {
					i.setPrice(Convert.numberToString(i.getPrice()));
				}
				controller.saveSaleBill(listItem);
				JOptionPane.showMessageDialog(null, "Lưu hóa đơn thành công");
				listItem.removeAll(listItem);
				table_1.setModel(new TableModel(convertListItem(listItem)));
				clearAll();
			}
		});
		btnLuu.setBounds(602, 470, 89, 23);
		panelXuat.add(btnLuu);

		JLabel lblSoLuong = new JLabel("Số lượng\r\n");
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoLuong.setBounds(63, 61, 62, 17);
		panelXuat.add(lblSoLuong);

		comboBoxSoLuong = new JComboBox();
		comboBoxSoLuong.setBounds(169, 61, 50, 20);
		panelXuat.add(comboBoxSoLuong);

		JLabel lblGiaNhap = new JLabel("Giá nhập");
		lblGiaNhap.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiaNhap.setBounds(63, 115, 62, 17);
		panelXuat.add(lblGiaNhap);

		JLabel lblGiXuat = new JLabel("Giá xuất");
		lblGiXuat.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiXuat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiXuat.setBounds(297, 115, 62, 17);
		panelXuat.add(lblGiXuat);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setEditable(false);
		txtGiaNhap.setBounds(169, 115, 86, 20);
		panelXuat.add(txtGiaNhap);
		txtGiaNhap.setColumns(10);

		txtGiaXuat = new JTextField();
		txtGiaXuat.setBounds(405, 115, 86, 20);
		txtGiaXuat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String name = comboBoxTimSP.getEditor().getItem().toString();
				List<Item> listItem = ItemDAO.getItem("FROM Item where name = '" + name + "'");
				if (listItem.size() > 0 && !txtGiaXuat.getText().equals("")) {
					txtLoiNhuan.setText(Convert.stringToNumber(String.valueOf(
							Integer.parseInt(txtGiaXuat.getText()) - Integer.parseInt(listItem.get(0).getPrice()))));
				}
			}
		});
		panelXuat.add(txtGiaXuat);
		txtGiaXuat.setColumns(10);

		JLabel lblLoiNhuan = new JLabel("Lợi nhuận");
		lblLoiNhuan.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoiNhuan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoiNhuan.setBounds(549, 115, 62, 17);
		panelXuat.add(lblLoiNhuan);

		txtLoiNhuan = new JTextField();
		txtLoiNhuan.setEditable(false);
		txtLoiNhuan.setBounds(655, 115, 86, 20);

		panelXuat.add(txtLoiNhuan);
		txtLoiNhuan.setColumns(10);

		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setBounds(549, 429, 46, 14);
		panelXuat.add(lblTongTien);

		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setBounds(631, 426, 110, 20);
		panelXuat.add(txtTongTien);
		txtTongTien.setColumns(10);

		JPanel panelNhap = new JPanel();
		tabbedPane_1.addTab("Nhập", null, panelNhap, null);

		JPanel panelLichSu = new JPanel();
		tabbedPane_1.addTab("Lịch sử", null, panelLichSu, null);
		comboBoxTimSP.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String name = comboBoxTimSP.getEditor().getItem().toString();
				comboBoxTimSP.setModel(model.getList(name));
				comboBoxTimSP.showPopup();
				comboBoxTimSP.getEditor().setItem(name);
				((JTextComponent) comboBoxTimSP.getEditor().getEditorComponent()).select(name.length(), name.length());
			}
		});
//		comboBoxTimSP.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				String price = "";
//				int quantity = 0;
//				int index = 0;
//				String name = comboBoxTimSP.getEditor().getItem().toString();
//				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//					index = 0;
//					index = comboBoxSoLuong.getItemCount();
//					for (int i = 0; i < index; i++) {
//						comboBoxSoLuong.removeItemAt(0);
//					}
//					List<Item> list = ItemDAO.getItem("FROM Item where name = '" + name + "'");
//					if (list.size() > 0) {
//						quantity = list.get(0).getQuantity();
//						for (int i = 1; i <= quantity; i++) {
//							comboBoxSoLuong.addItem(i);
//						}
//						price = list.get(0).getPrice();
//						txtGiaNhap.setText(Convert.stringToNumber(price));
//					}
//				}
//				if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90 || e.getKeyCode() >= 96 && e.getKeyCode() <= 105
//						|| e.getKeyCode() == 8) {
//					if (name.trim().equals("") || name == null) {
//						try {
//							comboBoxTimSP.setModel(model.emptyList());
//						} catch (NullPointerException exception) {
//							exception.printStackTrace();
//						}
//					} else {
//						comboBoxTimSP.setModel(model.getList(name));
//					}
//					if (comboBoxTimSP.getItemCount() > 0) {
//						comboBoxTimSP.showPopup();
//						if (e.getKeyCode() != 8) {
//							((JTextComponent) comboBoxTimSP.getEditor().getEditorComponent()).select(name.length(),
//									comboBoxTimSP.getEditor().getItem().toString().length());
//
//						} else {
//							comboBoxTimSP.getEditor().setItem(name);
//
//						}
//					} else {
//						comboBoxTimSP.addItem(name);
//					}
//
//				}
//
//				super.keyReleased(e);
//			}
//
//		});

		JPanel panelThongKe = new JPanel();
		tabbedPane.addTab("Thống kê", null, panelThongKe, null);
	}

	private boolean compare(String s) {
		Object[] list = comboBoxTimSP.getComponents();
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

	public void clearAll() {
		comboBoxTimSP.removeAllItems();
		comboBoxSoLuong.removeAllItems();
		txtGiaNhap.setText("");
		txtGiaXuat.setText("");
		txtLoiNhuan.setText("");
		txtTongTien.setText("");
	}

	public void initData() {
		listStorage = new ArrayList<Item>();
		listStorage = ItemDAO.getItemes();
		convertListItem(listStorage);

	}

	public List<Item> convertListItem(List<Item> list) {
		for (Item i : list) {
			i.setPrice(Convert.stringToNumber(i.getPrice()));
		}
		return list;
	}
}
