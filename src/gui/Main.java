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
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
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
import controller.FeeTableModel;
import controller.ItemTableModel;
import controller.MainController;
import dao.FeeDAO;
import dao.ItemDAO;
import lib.Convert;
import model.Fee;
import model.Item;

public class Main {

	private JFrame frame;
	private JTable tableTonKho;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxTimSP;
	ComboBoxModel model = new ComboBoxModel();
	MainController controller;
	private List<Item> listItem = new ArrayList<Item>();
	private JTable tableXuat;
	private JTextField txtGiaNhap;
	private JTextField txtGiaXuat;
	private JTextField txtLoiNhuan;
	@SuppressWarnings("rawtypes")
	JComboBox comboBoxSoLuong;
	int index = 0;
	private JTextField txtTongTien;
	private JRadioButton rdbtnTenSP;
	private JRadioButton rdbtnSoImei;
	int warehouse;
	private JTextField txtSearch;
	private JTextField txtTongTienKho;
	private List<Item> listStorage;
	private List<Fee> listFee;
	private int tongTienKho;
	private JTextField txtKhachHang;
	private JTextField txtDienThoai;
	private JTextField txtMuc;
	private JTextField txtSoTien;
	private JTextField txtFrom;
	private JTextField txtTo;
	private JTable tableLichSu;
	private JTextField txtTongThu;
	private JTextField txtTongChi;
	private JTextField txtLoiNhuanThuChi;
	private JRadioButton rdbtnThu;
	private JRadioButton rdbtnChi;
	private String from;
	private String to;

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxTimSP() {
		return comboBoxTimSP;
	}

	public void setComboBoxTimSP(@SuppressWarnings("rawtypes") JComboBox comboBoxTimSP) {
		this.comboBoxTimSP = comboBoxTimSP;
	}

	public JTable getTableTonKho() {
		return tableTonKho;
	}

	public void setTableTonKho(JTable tableTonKho) {
		this.tableTonKho = tableTonKho;
	}

	public JTable getTableXuat() {
		return tableXuat;
	}

	public void setTableXuat(JTable tableXuat) {
		this.tableXuat = tableXuat;
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

		tableTonKho = new JTable();
		tableTonKho.setBounds(10, 100, 750, 375);
		tableTonKho.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPaneTonKho = new JScrollPane(tableTonKho);
		scrollPaneTonKho.setBounds(10, 119, 750, 375);
		try {

			tableTonKho.setModel(new ItemTableModel(listStorage) {
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] { false, false, false, true, true };

				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		tableTonKho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (true) {
			ListSelectionModel rowSM = tableTonKho.getSelectionModel();
			rowSM.addListSelectionListener(new ListSelectionListener() {

				public void valueChanged(ListSelectionEvent e) {
					if (e.getValueIsAdjusting()) {
						return;
					}
					ListSelectionModel lsm = (ListSelectionModel) e.getSource();
					if (lsm.isSelectionEmpty()) {
						System.out.println("No rows are selected.");
					} else {
						// Item item =
						// ItemDAO.getItemes().get(lsm.getMinSelectionIndex());
						// ItemDetail itemDetail =
						// ItemDetailDAO.getItemDetail(item.getItemId());

					}
				}
			});
		}
		panelTonKho.add(scrollPaneTonKho);

		rdbtnTenSP = new JRadioButton("Tên sản phẩm");
		rdbtnTenSP.setBounds(120, 7, 109, 23);
		panelTonKho.add(rdbtnTenSP);
		rdbtnTenSP.setSelected(true);

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
					tableTonKho.setModel(new ItemTableModel(Convert.convertListItem(listStorage)));
					tongTienKho = 0;
					for (Item i : Convert.returnListItem(listStorage)) {
						tongTienKho += Integer.parseInt(i.getPrice()) * i.getQuantity();
					}
					txtTongTienKho.setText(Convert.numberToString(String.valueOf(tongTienKho)));
					warehouse = 0;
				} else if (e.getItem().equals("Điện thoại")) {
					listStorage = ItemDAO.getItem("From Item where type = 'Điện thoại'");
					tableTonKho.setModel(new ItemTableModel(Convert.convertListItem(listStorage)));
					tongTienKho = 0;
					for (Item i : Convert.returnListItem(listStorage)) {
						tongTienKho += Integer.parseInt(i.getPrice()) * i.getQuantity();
					}
					txtTongTienKho.setText(Convert.numberToString(String.valueOf(tongTienKho)));
					warehouse = 1;
				} else if (e.getItem().equals("Máy tính bảng")) {
					listStorage = ItemDAO.getItem("From Item where type = 'Máy tính bảng'");
					tableTonKho.setModel(new ItemTableModel(Convert.convertListItem(listStorage)));
					tongTienKho = 0;
					for (Item i : Convert.returnListItem(listStorage)) {
						tongTienKho += Integer.parseInt(i.getPrice()) * i.getQuantity();
					}
					txtTongTienKho.setText(Convert.numberToString(String.valueOf(tongTienKho)));
					warehouse = 2;
				} else if (e.getItem().equals("Linh kiện")) {
					listStorage = ItemDAO.getItem("From Item where type = 'Linh kiện'");
					tableTonKho.setModel(new ItemTableModel(Convert.convertListItem(listStorage)));
					tongTienKho = 0;
					for (Item i : Convert.returnListItem(listStorage)) {
						tongTienKho += Integer.parseInt(i.getPrice()) * i.getQuantity();
					}
					txtTongTienKho.setText(Convert.numberToString(String.valueOf(tongTienKho)));
					warehouse = 3;
				} else if (e.getItem().equals("Phụ kiện")) {
					listStorage = ItemDAO.getItem("From Item where type = 'Phụ kiện'");
					tableTonKho.setModel(new ItemTableModel(Convert.convertListItem(listStorage)));
					tongTienKho = 0;
					for (Item i : Convert.returnListItem(listStorage)) {
						tongTienKho += Integer.parseInt(i.getPrice()) * i.getQuantity();
					}
					txtTongTienKho.setText(Convert.numberToString(String.valueOf(tongTienKho)));
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
					tableTonKho.setModel(new ItemTableModel(listStorage));
				} else {
					tableTonKho.setModel(new ItemTableModel(controller.searchItem(listStorage, txtSearch.getText())));
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Tổng tiền");
		lblNewLabel.setBounds(561, 507, 50, 20);
		panelTonKho.add(lblNewLabel);

		txtTongTienKho = new JTextField();
		txtTongTienKho.setEditable(false);
		txtTongTienKho.setBounds(628, 507, 132, 20);
		for (Item i : Convert.returnListItem(listStorage)) {
			tongTienKho += Integer.parseInt(i.getPrice()) * i.getQuantity();
		}
		txtTongTienKho.setText(Convert.numberToString(String.valueOf(tongTienKho)));
		panelTonKho.add(txtTongTienKho);
		txtTongTienKho.setColumns(10);

		JPanel panelNhapXuat = new JPanel();
		tabbedPane.addTab("Nhập/Xuất", null, panelNhapXuat, null);
		panelNhapXuat.setLayout(null);

		JTabbedPane tabbedPaneNhapXuat = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneNhapXuat.setBounds(0, 0, 769, 532);
		panelNhapXuat.add(tabbedPaneNhapXuat);

		JPanel panelXuat = new JPanel();

		tabbedPaneNhapXuat.addTab("Xuất", null, panelXuat, null);
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
		comboBoxTimSP.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String price = "";
				int quantity = 0;
				String name = comboBoxTimSP.getEditor().getItem().toString();
				if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90 || e.getKeyCode() >= 96 && e.getKeyCode() <= 105
						|| e.getKeyCode() == 8) {
					comboBoxTimSP.setModel(model.getList(name));
					comboBoxTimSP.showPopup();
					comboBoxTimSP.getEditor().setItem(name);
					((JTextComponent) comboBoxTimSP.getEditor().getEditorComponent()).select(name.length(),
							name.length());
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					index = comboBoxSoLuong.getItemCount();
					for (int i = 0; i < index; i++) {
						comboBoxSoLuong.removeItemAt(0);
					}
					List<Item> list = ItemDAO.getItem("FROM Item where name = '" + name + "'");
					if (list.size() > 0) {
						quantity = list.get(0).getQuantity();
						for (int i = 1; i <= quantity; i++) {
							comboBoxSoLuong.addItem(i);
						}
						price = list.get(0).getPrice();
						txtGiaNhap.setText(Convert.numberToString(price));
					}
				}
			}
		});

		tableXuat = new JTable();
		tableXuat.setBounds(10, 150, 745, 262);
		JScrollPane scrollPaneXuat = new JScrollPane(tableXuat);
		scrollPaneXuat.setBounds(10, 157, 750, 262);
		tableXuat.setEditingColumn(3);
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
				if (!txtGiaXuat.getText().trim().equals("")) {
					i.setPrice(txtGiaXuat.getText().trim());
					listItem.add(i);
					i.setPrice(Convert.numberToString(txtGiaXuat.getText().trim()));
					try {
						tableXuat.setModel(new ItemTableModel(listItem) {

							/**
							 * 
							 */
							private static final long serialVersionUID = 2928691229876020678L;
						});

					} catch (Exception e) {
						e.printStackTrace();
					}
					index++;
					int total = 0;
					for (Item item : listItem) {
						total += Integer.parseInt(Convert.stringToNumber(item.getPrice())) * item.getQuantity();
					}
					txtTongTien.setText(Convert.numberToString(String.valueOf(total)));
				} else {
					JOptionPane.showMessageDialog(null, "Chưa nhập giá xuất", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelXuat.add(btnThem);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller = new MainController();
				for (Item i : listItem) {
					i.setPrice(Convert.stringToNumber(i.getPrice()));
				}
				controller.saveSaleBill(listItem);
				JOptionPane.showMessageDialog(null, "Lưu hóa đơn thành công");
				listItem.removeAll(listItem);
				tableXuat.setModel(new ItemTableModel(Convert.convertListItem(listItem)));
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
		lblGiXuat.setBounds(297, 61, 62, 17);
		panelXuat.add(lblGiXuat);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setEditable(false);
		txtGiaNhap.setBounds(169, 115, 86, 20);
		panelXuat.add(txtGiaNhap);
		txtGiaNhap.setColumns(10);

		txtGiaXuat = new JTextField();
		txtGiaXuat.setBounds(405, 61, 86, 20);
		txtGiaXuat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String name = comboBoxTimSP.getEditor().getItem().toString();
				List<Item> listItem = ItemDAO.getItem("FROM Item where name = '" + name + "'");
				if (listItem.size() > 0 && !txtGiaXuat.getText().equals("")) {
					txtLoiNhuan.setText(Convert.numberToString(String.valueOf(
							Integer.parseInt(txtGiaXuat.getText()) - Integer.parseInt(listItem.get(0).getPrice()))));
				}
			}
		});
		panelXuat.add(txtGiaXuat);
		txtGiaXuat.setColumns(10);

		JLabel lblLoiNhuan = new JLabel("Lợi nhuận");
		lblLoiNhuan.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoiNhuan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoiNhuan.setBounds(297, 115, 62, 17);
		panelXuat.add(lblLoiNhuan);

		txtLoiNhuan = new JTextField();
		txtLoiNhuan.setEditable(false);
		txtLoiNhuan.setBounds(405, 115, 86, 20);

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

		JLabel lblKhachHang = new JLabel("Khách hàng");
		lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhachHang.setBounds(547, 61, 80, 17);
		panelXuat.add(lblKhachHang);

		txtKhachHang = new JTextField();
		txtKhachHang.setColumns(10);
		txtKhachHang.setBounds(655, 61, 86, 20);
		panelXuat.add(txtKhachHang);

		JLabel lblDienThoai = new JLabel("Điện thoại");
		lblDienThoai.setHorizontalAlignment(SwingConstants.CENTER);
		lblDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDienThoai.setBounds(547, 112, 62, 17);
		panelXuat.add(lblDienThoai);

		txtDienThoai = new JTextField();
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(655, 112, 86, 20);
		panelXuat.add(txtDienThoai);

		// JPanel panelNhap = new JPanel();
		// tabbedPane_1.addTab("Nhập", null, panelNhap, null);

		// JPanel panelLichSu = new JPanel();
		// tabbedPane_1.addTab("Lịch sử", null, panelLichSu, null);

		JPanel panelThuChi = new JPanel();
		tabbedPane.addTab("Thu/Chi", null, panelThuChi, null);
		panelThuChi.setLayout(null);

		JPanel panelGhi = new JPanel();
		panelGhi.setBounds(0, 0, 765, 182);
		panelThuChi.add(panelGhi);
		panelGhi.setLayout(null);

		JLabel lblMuc = new JLabel("Mục");
		lblMuc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMuc.setBounds(20, 20, 50, 25);
		panelGhi.add(lblMuc);

		JLabel lblSoTien = new JLabel("Số tiền");
		lblSoTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoTien.setBounds(20, 78, 50, 25);
		panelGhi.add(lblSoTien);

		txtMuc = new JTextField();
		txtMuc.setColumns(10);
		txtMuc.setBounds(95, 20, 200, 25);
		panelGhi.add(txtMuc);

		txtSoTien = new JTextField();
		txtSoTien.setColumns(10);
		txtSoTien.setBounds(95, 78, 200, 25);
		panelGhi.add(txtSoTien);

		JButton btnLu = new JButton("Lưu");
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fee fee = new Fee();
				fee.setName(txtMuc.getText().toString().trim());
				fee.setValue(txtSoTien.getText().toString().trim());
				if (rdbtnThu.isSelected()) {
					fee.setType(true);
				} else {
					fee.setType(false);
				}
				fee.setDate(new Date());
				FeeDAO.insert(fee);
				JOptionPane.showMessageDialog(null, "Lưu thành công");
				txtMuc.setText("");
				txtSoTien.setText("");
				listFee = FeeDAO.getFees();
				tableLichSu.setModel(new FeeTableModel(Convert.convertListFee(listFee)));
				setValues();
			}
		});
		btnLu.setBounds(705, 148, 50, 23);
		panelGhi.add(btnLu);

		rdbtnThu = new JRadioButton("Thu");
		rdbtnThu.setSelected(true);
		rdbtnThu.setBounds(632, 7, 50, 23);
		panelGhi.add(rdbtnThu);

		rdbtnChi = new JRadioButton("Chi");
		rdbtnChi.setBounds(705, 7, 50, 23);
		panelGhi.add(rdbtnChi);

		ButtonGroup groupThuChi = new ButtonGroup();
		groupThuChi.add(rdbtnThu);
		groupThuChi.add(rdbtnChi);

		JPanel panelLichSu = new JPanel();
		panelLichSu.setBounds(0, 185, 765, 345);
		panelThuChi.add(panelLichSu);
		panelLichSu.setLayout(null);

		JLabel lblLichSu = new JLabel("Lịch sử");
		lblLichSu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLichSu.setBounds(10, 11, 50, 25);
		panelLichSu.add(lblLichSu);

		JLabel lblFrom = new JLabel("Từ");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFrom.setBounds(70, 24, 50, 25);
		panelLichSu.add(lblFrom);

		txtFrom = new JTextField();
		txtFrom.setColumns(10);
		txtFrom.setBounds(130, 23, 170, 25);
		txtFrom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					for (int i = 0; i < listFee.size(); i++) {
						listFee.remove(i);
					}
					from = Convert.getDate(txtFrom.getText().toString().trim());
					if (txtTo.getText().equals(Convert.formatDate(new Date()))) {
						to = Convert.getDate(Convert.formatDate(new Date()));
					} else {
						to = Convert.getDate(txtTo.getText().toString().trim());
					}
					System.out.println(from + "   " + to);
					if (from.equals("")) {
						listFee = FeeDAO.getFee("From Fee where day <= '" + to + "'");
					} else if (!FeeDAO.getByDate(from, to).isEmpty()) {
						listFee = FeeDAO.getByDate(from, to);
						tableLichSu.setModel(new FeeTableModel(Convert.convertListFee(listFee)));
					}
				}
			}
		});
		panelLichSu.add(txtFrom);

		JLabel lblTo = new JLabel("Đến");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTo.setBounds(346, 24, 50, 25);
		panelLichSu.add(lblTo);

		txtTo = new JTextField();
		txtTo.setColumns(10);
		txtTo.setBounds(406, 23, 170, 25);
		txtTo.setText(Convert.formatDate(new Date()));
		txtTo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					for (int i = 0; i < listFee.size(); i++) {
						listFee.remove(i);
					}
					from = Convert.getDate(txtFrom.getText().toString().trim());
					if (txtTo.getText().equals(Convert.formatDate(new Date()))) {
						to = Convert.getDate(Convert.formatDate(new Date()));
					} else {
						to = Convert.getDate(txtTo.getText().toString().trim());
					}
					System.out.println(from + "   " + to);
					if (from.equals("")) {
						listFee = FeeDAO.getFee("From Fee where day <= '" + to + "'");
					} else if (!FeeDAO.getByDate(from, to).isEmpty()) {
						listFee = FeeDAO.getByDate(from, to);
						tableLichSu.setModel(new FeeTableModel(Convert.convertListFee(listFee)));
					}
				}
			}
		});
		panelLichSu.add(txtTo);

		tableLichSu = new JTable();
		tableLichSu.setBounds(10, 344, 745, 262);
		tableLichSu.setModel(new FeeTableModel(listFee));

		JScrollPane scrollPaneLichSu = new JScrollPane(tableLichSu);
		scrollPaneLichSu.setBounds(10, 72, 745, 215);
		panelLichSu.add(scrollPaneLichSu);

		JLabel lblTongThu = new JLabel("Tổng thu");
		lblTongThu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTongThu.setBounds(46, 309, 60, 14);
		panelLichSu.add(lblTongThu);

		txtTongThu = new JTextField();
		txtTongThu.setEditable(false);
		txtTongThu.setBounds(130, 306, 90, 22);
		panelLichSu.add(txtTongThu);
		txtTongThu.setColumns(10);

		JLabel lblTongChi = new JLabel("Tổng chi");
		lblTongChi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTongChi.setBounds(278, 309, 60, 14);
		panelLichSu.add(lblTongChi);

		txtTongChi = new JTextField();
		txtTongChi.setEditable(false);
		txtTongChi.setColumns(10);
		txtTongChi.setBounds(362, 306, 90, 22);
		panelLichSu.add(txtTongChi);

		JLabel lblLoiNhuanThuChi = new JLabel("Lợi nhuận");
		lblLoiNhuanThuChi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoiNhuanThuChi.setBounds(516, 309, 60, 14);
		panelLichSu.add(lblLoiNhuanThuChi);

		txtLoiNhuanThuChi = new JTextField();
		txtLoiNhuanThuChi.setEditable(false);
		txtLoiNhuanThuChi.setColumns(10);
		txtLoiNhuanThuChi.setBounds(600, 306, 90, 22);
		panelLichSu.add(txtLoiNhuanThuChi);
		setValues();

		JButton btnTtC = new JButton("Tất cả");
		btnTtC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listFee = FeeDAO.getFees();
				tableLichSu.setModel(new FeeTableModel(Convert.convertListFee(listFee)));
				setValues();
			}
		});
		btnTtC.setBounds(692, 27, 63, 23);
		panelLichSu.add(btnTtC);

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
		Convert.convertListItem(listStorage);

		listFee = new ArrayList<>();
		listFee = FeeDAO.getFees();
		Convert.convertListFee(listFee);

	}

	private void setValues() {
		List<Fee> list = FeeDAO.getFees();
		int tongThu = 0;
		int tongChi = 0;
		for (Fee e : list) {
			if (e.isType()) {
				tongThu += Integer.parseInt(e.getValue());
			} else {
				tongChi += Integer.parseInt(e.getValue());
			}
		}
		int loiNhuan = tongThu - tongChi;
		txtTongThu.setText(Convert.numberToString(tongThu + ""));
		txtTongChi.setText(Convert.numberToString(tongChi + ""));
		txtLoiNhuanThuChi.setText(Convert.numberToString(loiNhuan + ""));
	}

}
