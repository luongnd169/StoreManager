package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.CustomerTableModel;
import dao.CustomerDAO;
import model.Customer;

public class AddCustomer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtTen;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JButton btnThem;
	private JButton btnHuy;
	private JCheckBox chckbxKhach;

	public AddCustomer(JTable table) {
		initialize(table);
	}

	private void initialize(final JTable table) {
		setBounds(100, 100, 450, 300);
		setVisible(true);
		getContentPane().setLayout(null);

		JLabel lblTen = new JLabel("Tên khách hàng");
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTen.setBounds(10, 28, 100, 20);
		getContentPane().add(lblTen);

		txtTen = new JTextField();
		txtTen.setBounds(120, 29, 250, 20);
		getContentPane().add(txtTen);
		txtTen.setColumns(10);

		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSDT.setBounds(10, 89, 100, 20);
		getContentPane().add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(120, 90, 250, 20);
		getContentPane().add(txtSDT);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiaChi.setBounds(10, 147, 100, 20);
		getContentPane().add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(120, 148, 250, 20);
		getContentPane().add(txtDiaChi);

		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtTen.getText();
				String phone = txtSDT.getText();
				String address = txtDiaChi.getText();
				boolean provider;
				if(chckbxKhach.isSelected()){
					provider = false;
				} else {
					provider = true;
				}
				
				if(CustomerDAO.getCustomer("From Customer where phone ='" + phone + "'").isEmpty()){
				Customer c = new Customer();
				c.setName(name);
				c.setPhone(phone);
				c.setAddress(address);
				c.setProvider(provider);
				CustomerDAO.insert(c);
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				table.setModel(new CustomerTableModel(CustomerDAO.getCustomers()));
				} else {
					JOptionPane.showMessageDialog(null, "Khách hàng đã tồn tại");
				}
			}
		});
		btnThem.setBounds(120, 211, 89, 23);
		getContentPane().add(btnThem);

		btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnHuy.setBounds(281, 211, 89, 23);
		getContentPane().add(btnHuy);

		chckbxKhach = new JCheckBox("Khách");
		chckbxKhach.setBounds(366, 5, 70, 23);
		getContentPane().add(chckbxKhach);
	}

}
