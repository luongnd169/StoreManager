package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.CustomerDAO;
import dao.ItemDAO;
import dao.ItemDetailDAO;
import lib.Convert;
import model.Customer;
import model.Item;
import model.ItemDetail;

public class AddItem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtTenSanPham;
	private JTextField txtGiaNhap;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxNhaCungCap;
	private JTextField txtImei;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxLoai;
	private JTextField txtSoLuong;
	private JButton btnLuu;
	private JButton btnHuy;

	public JTextField getTxtTenSanPham() {
		return txtTenSanPham;
	}

	public void setTxtTenSanPham(JTextField txtTenSanPham) {
		this.txtTenSanPham = txtTenSanPham;
	}

	public JTextField getTxtGiaNhap() {
		return txtGiaNhap;
	}

	public void setTxtGiaNhap(JTextField txtGiaNhap) {
		this.txtGiaNhap = txtGiaNhap;
	}

	public JTextField getTxtSoLuong() {
		return txtSoLuong;
	}

	public void setTxtSoLuong(JTextField txtSoLuong) {
		this.txtSoLuong = txtSoLuong;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxNhaCungCap() {
		return comboBoxNhaCungCap;
	}

	@SuppressWarnings("rawtypes")
	public void setComboBoxNhaCungCap(JComboBox comboBoxNhaCungCap) {
		this.comboBoxNhaCungCap = comboBoxNhaCungCap;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxLoai() {
		return comboBoxLoai;
	}

	@SuppressWarnings("rawtypes")
	public void setComboBoxLoai(JComboBox comboBoxLoai) {
		this.comboBoxLoai = comboBoxLoai;
	}

	public JTextField getTxtImei() {
		return txtImei;
	}

	public void setTxtImei(JTextField txtImei) {
		this.txtImei = txtImei;
	}

	public JButton getBtnLuu() {
		return btnLuu;
	}

	public void setBtnLuu(JButton btnLuu) {
		this.btnLuu = btnLuu;
	}

	public JButton getBtnHuy() {
		return btnHuy;
	}

	public void setBtnHuy(JButton btnHuy) {
		this.btnHuy = btnHuy;
	}

	public AddItem() {
		initialize();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {

		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(null);
		setVisible(true);

		JLabel lblTenSanPham = new JLabel("Tên sản phẩm");
		lblTenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTenSanPham.setBounds(10, 10, 80, 18);
		getContentPane().add(lblTenSanPham);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setBounds(120, 10, 210, 20);
		getContentPane().add(txtTenSanPham);
		txtTenSanPham.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtSoLuong.select(0, 1);

				}
			}
		});
		txtTenSanPham.setColumns(10);

		JLabel lblLoai = new JLabel("Loại");
		lblLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoai.setBounds(10, 51, 80, 18);
		getContentPane().add(lblLoai);

		comboBoxLoai = new JComboBox();
		comboBoxLoai.setBounds(120, 51, 90, 20);
		comboBoxLoai.addItem("Điện thoại");
		comboBoxLoai.addItem("Máy tính bảng");
		comboBoxLoai.addItem("Linh kiện");
		comboBoxLoai.addItem("Phụ kiện");
		getContentPane().add(comboBoxLoai);

		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSoLuong.setBounds(220, 51, 50, 20);
		getContentPane().add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(280, 51, 50, 20);
		txtSoLuong.setText("1");
		getContentPane().add(txtSoLuong);
		txtSoLuong.setColumns(10);

		JLabel lblGiaNhap = new JLabel("Giá nhập");
		lblGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGiaNhap.setBounds(10, 96, 80, 18);
		getContentPane().add(lblGiaNhap);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(120, 96, 210, 20);
		getContentPane().add(txtGiaNhap);

		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp");
		lblNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNhaCungCap.setBounds(10, 140, 80, 18);
		getContentPane().add(lblNhaCungCap);

		comboBoxNhaCungCap = new JComboBox();
		comboBoxNhaCungCap.setBounds(120, 140, 210, 20);
		List<Customer> listCustomer = CustomerDAO.getCustomer("From Customer where provider = 1");
		for (Customer c : listCustomer) {
			comboBoxNhaCungCap.addItem(c.getName());
		}
		getContentPane().add(comboBoxNhaCungCap);

		JLabel lblImei = new JLabel("Imei");
		lblImei.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblImei.setBounds(10, 183, 80, 18);
		getContentPane().add(lblImei);

		setTxtImei(new JTextField());
		getTxtImei().setColumns(10);
		getTxtImei().setBounds(120, 183, 210, 20);
		getContentPane().add(getTxtImei());

		btnLuu = new JButton("Lưu");
		btnLuu.setBounds(122, 227, 89, 23);
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer itemId = -1;
				String name = txtTenSanPham.getText();
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "Chưa nhập tên");
					return;
				}
				String type = comboBoxLoai.getSelectedItem().toString();
				int quantity = Integer.parseInt(txtSoLuong.getText());
				String price = txtGiaNhap.getText();
				if (price.equals("")) {
					JOptionPane.showMessageDialog(null, "Chưa nhập giá");
					return;
				}
				String provider = comboBoxNhaCungCap.getSelectedItem().toString();
				String imeis = "";
				imeis = txtImei.getText();
				if (imeis.equals("")) {
					JOptionPane.showMessageDialog(null, "Chưa nhập imei");
					return;
				}
				StringTokenizer st = new StringTokenizer(imeis, " ");
				String[] imei = new String[quantity];
				if (st.countTokens() != quantity) {
					JOptionPane.showMessageDialog(null, "Dữ liệu sai");
				} else {
					for (int i = 0; i < imei.length; i++) {
						imei[i] = st.nextToken();
					}

					Item item = new Item();
					item.setName(name);
					item.setType(type);
					if (txtTenSanPham.isEditable()) {
						itemId = ItemDAO.getNextId();
						item.setItemId(itemId);
						item.setQuantity(quantity);
						item.setPrice(price);
						ItemDAO.insert(item);
					} else {
						Item i = ItemDAO.getItem("From Item where name ='" + name + "'").get(0);
						itemId = i.getItemId();
						item.setItemId(itemId);
						item.setQuantity(i.getQuantity() + quantity);
						int averagePrice = (Integer.parseInt(i.getPrice()) + Integer.parseInt(price))/2;
						item.setPrice(String.valueOf(averagePrice));
						ItemDAO.update(item);
					}

					ItemDetail itemDetail;
					for (int i = 0; i < quantity; i++) {
						itemDetail = new ItemDetail();
						itemDetail.setItemId(itemId);
						itemDetail.setImei(imei[i]);
						itemDetail.setImportDate(Convert.formatDateSQL(new Date()));
						itemDetail.setImportPrice(price);
						itemDetail.setProvider(
								CustomerDAO.getCustomer("From Customer where name ='" + provider + "' and provider = 1")
										.get(0).getCustomerId());
						itemDetail.setStatus(true);
						ItemDetailDAO.insert(itemDetail);
					}
				}

			}
		});
		getContentPane().add(btnLuu);

		btnHuy = new JButton("Hủy");
		btnHuy.setBounds(241, 227, 89, 23);
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnHuy);
	}

	public static void main(String[] args) {
		new AddItem();
	}

}
