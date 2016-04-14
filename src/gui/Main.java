package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
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
import controller.TableModel;
import dao.ItemDAO;

public class Main {

	private JFrame frame;
	private JTextField txtSearch;
	private JTable table;
	private JComboBox comboBox_1;
	ComboBoxModel model = new ComboBoxModel();

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * Launch the application.
	 * 
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 */
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		// Auto Complete Combobox Java
		// Enable Editing Combobox Java
		// EventQueue.invokeLater(new Runnable() {
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
				boolean[] columnEditables = new boolean[] { false, false, false, false, false, true, false };

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

		JLabel lblName = new JLabel("Sản phẩm");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(10, 11, 100, 40);
		panel_2.add(lblName);

		List<String> listName = ItemDAO.getItemName();
		for (String s : listName) {
			System.out.println(s);
		}
		comboBox_1 = new JComboBox<>();
		comboBox_1.setBounds(120, 17, 250, 32);
		comboBox_1.setEditable(true);
		comboBox_1.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String s = comboBox_1.getEditor().getItem().toString();
				// if(e.getKeyCode() == KeyEvent.VK_ENTER){
				// if(comparar(s)){
				//
				// }
				// } else {
				// comboBox_1.setSelectedIndex(0);
				// }

				if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90 || e.getKeyCode() >= 96 && e.getKeyCode() <= 105
						|| e.getKeyCode() == 8) {
					comboBox_1.setModel(model.getList(s));
					if (comboBox_1.getItemCount() > 0) {
						comboBox_1.showPopup();
						if (e.getKeyCode() != 8) {
							((JTextComponent) comboBox_1.getEditor().getEditorComponent()).select(s.length(),
									comboBox_1.getEditor().getItem().toString().length());
							;
						} else {
							comboBox_1.getEditor().setItem(s);
						}
					} else {
						comboBox_1.addItem(s);
					}
				}

				super.keyReleased(e);
			}

		});
		panel_2.add(comboBox_1);

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
