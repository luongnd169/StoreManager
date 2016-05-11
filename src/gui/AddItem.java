package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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

	public JComboBox getComboBoxNhaCungCap() {
		return comboBoxNhaCungCap;
	}

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

		JLabel lblTenSanPham = new JLabel("Tên sản phẩm");
		lblTenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTenSanPham.setBounds(10, 10, 80, 18);
		getContentPane().add(lblTenSanPham);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setBounds(120, 10, 210, 20);
		getContentPane().add(txtTenSanPham);
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
		comboBoxLoai.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().equals("Điện thoại")) {
					getTxtSoLuong().setEditable(false);
					getTxtSoLuong().setText("1");
					txtImei.setEditable(true);
				} else if (e.getItem().equals("Máy tính bảng")) {
					getTxtSoLuong().setEditable(false);
					getTxtSoLuong().setText("1");
					txtImei.setEditable(true);
				} else if (e.getItem().equals("Linh kiện")) {
					getTxtSoLuong().setEditable(true);
					getTxtSoLuong().setText("1");
					txtImei.setEditable(false);
				} else if (e.getItem().equals("Phụ kiện")) {
					getTxtSoLuong().setEditable(true);
					getTxtSoLuong().setText("1");
					txtImei.setEditable(false);
				}

			}
		});
		getContentPane().add(comboBoxLoai);

		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSoLuong.setBounds(220, 51, 50, 20);
		getContentPane().add(lblSoLuong);

		setTxtSoLuong(new JTextField());
		getTxtSoLuong().setBounds(280, 51, 50, 20);
		getTxtSoLuong().setText("1");
		getTxtSoLuong().setEditable(false);
		getContentPane().add(getTxtSoLuong());
		getTxtSoLuong().setColumns(10);

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
		AddItem addItem = new AddItem();
		addItem.setBounds(100, 100, 400, 300);
		addItem.getContentPane().setLayout(null);
		addItem.setVisible(true);
	}
}
