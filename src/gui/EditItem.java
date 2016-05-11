package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EditItem extends JFrame {

	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtNhaCungCap;
	private JTextField txtSoLuongTon;
	private JTextField txtGiaNhap;
	private JTextField txtGiaBinhQuan;
	private JTextField txtNgayNhap;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxImei;

	public JTextField getTxtMaSP() {
		return txtMaSP;
	}

	public void setTxtMaSP(JTextField txtMaSP) {
		this.txtMaSP = txtMaSP;
	}

	public JTextField getTxtTenSP() {
		return txtTenSP;
	}

	public void setTxtTenSP(JTextField txtTenSP) {
		this.txtTenSP = txtTenSP;
	}

	public JTextField getTxtNhaCungCap() {
		return txtNhaCungCap;
	}

	public void setTxtNhaCungCap(JTextField txtNhaCungCap) {
		this.txtNhaCungCap = txtNhaCungCap;
	}

	public JTextField getTxtSoLuongTon() {
		return txtSoLuongTon;
	}

	public void setTxtSoLuongTon(JTextField txtSoLuongTon) {
		this.txtSoLuongTon = txtSoLuongTon;
	}

	public JTextField getTxtGiaNhap() {
		return txtGiaNhap;
	}

	public void setTxtGiaNhap(JTextField txtGiaNhap) {
		this.txtGiaNhap = txtGiaNhap;
	}

	public JTextField getTxtGiaBinhQuan() {
		return txtGiaBinhQuan;
	}

	public void setTxtGiaBinhQuan(JTextField txtGiaBinhQuan) {
		this.txtGiaBinhQuan = txtGiaBinhQuan;
	}

	public JTextField getTxtNgayNhap() {
		return txtNgayNhap;
	}

	public void setTxtNgayNhap(JTextField txtNgayNhap) {
		this.txtNgayNhap = txtNgayNhap;
	}

	public JComboBox getComboBoxImei() {
		return comboBoxImei;
	}

	public void setComboBoxImei(JComboBox comboBoxImei) {
		this.comboBoxImei = comboBoxImei;
	}

	/**
	 * Create the application.
	 */
	public EditItem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() {

		JLabel lblThongTinSP = new JLabel("Thông tin sản phẩm");
		lblThongTinSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThongTinSP.setBounds(220, 22, 134, 18);
		getContentPane().add(lblThongTinSP);

		JLabel lblMaSP = new JLabel("Mã sản phẩm");
		lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMaSP.setBounds(20, 82, 80, 17);
		getContentPane().add(lblMaSP);

		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		txtMaSP.setBounds(110, 80, 150, 20);
		getContentPane().add(txtMaSP);
		txtMaSP.setColumns(10);

		JLabel lblTenSP = new JLabel("Tên sản phẩm");
		lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTenSP.setBounds(20, 142, 80, 17);
		getContentPane().add(lblTenSP);

		txtTenSP = new JTextField();
		txtTenSP.setEditable(false);
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(110, 140, 150, 20);
		getContentPane().add(txtTenSP);

		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp");
		lblNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNhaCungCap.setBounds(20, 200, 80, 20);
		getContentPane().add(lblNhaCungCap);

		txtNhaCungCap = new JTextField();
		txtNhaCungCap.setEditable(false);
		txtNhaCungCap.setColumns(10);
		txtNhaCungCap.setBounds(110, 200, 150, 20);
		getContentPane().add(txtNhaCungCap);

		JLabel lblSoLuongTon = new JLabel("Số lượng tồn");
		lblSoLuongTon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSoLuongTon.setBounds(20, 260, 80, 20);
		getContentPane().add(lblSoLuongTon);

		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setColumns(10);
		txtSoLuongTon.setBounds(110, 260, 150, 20);
		txtSoLuongTon.setEditable(false);
		getContentPane().add(txtSoLuongTon);

		JLabel lblGiaNhap = new JLabel("Giá nhập");
		lblGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGiaNhap.setBounds(310, 82, 80, 17);
		getContentPane().add(lblGiaNhap);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(400, 80, 150, 20);
		txtGiaNhap.setEditable(false);
		getContentPane().add(txtGiaNhap);

		JLabel lblGiaBinhQuan = new JLabel("Gía bình quân");
		lblGiaBinhQuan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGiaBinhQuan.setBounds(310, 142, 80, 17);
		getContentPane().add(lblGiaBinhQuan);

		txtGiaBinhQuan = new JTextField();
		txtGiaBinhQuan.setEditable(false);
		txtGiaBinhQuan.setColumns(10);
		txtGiaBinhQuan.setBounds(400, 140, 150, 20);
		getContentPane().add(txtGiaBinhQuan);

		JLabel lblNgayNhap = new JLabel("Ngày nhập");
		lblNgayNhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNgayNhap.setBounds(310, 202, 80, 17);
		getContentPane().add(lblNgayNhap);

		txtNgayNhap = new JTextField();
		txtNgayNhap.setColumns(10);
		txtNgayNhap.setBounds(400, 200, 150, 20);
		txtNgayNhap.setEditable(false);
		getContentPane().add(txtNgayNhap);

		JLabel lblSoImei = new JLabel("Số Imei");
		lblSoImei.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSoImei.setBounds(310, 262, 80, 17);
		getContentPane().add(lblSoImei);

		comboBoxImei = new JComboBox();
//		comboBoxImei.setEditable(true);
		comboBoxImei.setBounds(400, 260, 150, 20);
		getContentPane().add(comboBoxImei);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.setBounds(134, 310, 89, 23);
		getContentPane().add(btnLuu);

		JButton btnDong = new JButton("Đóng");
		btnDong.setBounds(365, 310, 89, 23);
		btnDong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		getContentPane().add(btnDong);
	}
}
