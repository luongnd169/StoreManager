package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.Item;

public class TableModel extends AbstractTableModel {

	private Vector colHeaders;
	private Vector tbData;
	String[] colsName = { "Mã sản phẩm", "Tên sản phẩm", "Loại", "Số lượng", "Giá" };
	
	public TableModel(List<Item> list) {
		int count = colsName.length;
		colHeaders = new Vector(count);
		tbData = new Vector();
		for (int i = 0; i < count; i++) {
			colHeaders.addElement(colsName[i]);
		}

		for (int i = 0; i < list.size(); i++) {
			Vector dataRow = new Vector(count);
			dataRow.addElement(list.get(i).getModel());
			dataRow.addElement(list.get(i).getName());
			dataRow.addElement(list.get(i).getType());
			dataRow.addElement(list.get(i).getQuantity());
			dataRow.addElement(list.get(i).getPrice());
			tbData.addElement(dataRow);

		}
	}

	@Override
	public int getColumnCount() {
		// return colHeaders.size();
		return colsName.length;
	}

	@Override
	public int getRowCount() {
		return tbData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Vector rowData = (Vector) (tbData.elementAt(row));
		return rowData.elementAt(col);
	}

	@Override
	public String getColumnName(int column) {
		// return (String) colHeaders.elementAt(column);
		return colsName[column];
	}

}
