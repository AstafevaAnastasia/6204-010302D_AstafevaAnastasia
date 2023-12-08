package ui;

import packFunctions.TabulatedFunction;
import javax.swing.table.AbstractTableModel;

public class TabulatedFunctionTableModel extends AbstractTableModel {

    private final TabulatedFunction function;

    public TabulatedFunctionTableModel(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public int getRowCount() {
        return function.getCount(); // ���������� ����� ����� ���������� ����� � ��������� �������
    }

    @Override
    public int getColumnCount() {
        return 2; // ��� �������: ���� ��� x, ������ ��� y
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return function.getX(rowIndex); // �������� �������� x ��� ������ ������
        } else {
            return function.getY(rowIndex); // �������� �������� y ��� ������ ������
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            function.setY(rowIndex, (double) value); // ������������� ����� �������� y ��� ������ ������
            fireTableCellUpdated(rowIndex, columnIndex); // ��������� ������
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1; // ������ ������ ������� y �������� ��������������
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "X";
        } else {
            return "Y";
        }
    }
}
