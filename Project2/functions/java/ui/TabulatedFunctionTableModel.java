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
        return function.getCount(); // количество строк равно количеству точек в табличной функции
    }

    @Override
    public int getColumnCount() {
        return 2; // два столбца: один дл€ x, другой дл€ y
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return function.getX(rowIndex); // получаем значение x дл€ данной строки
        } else {
            return function.getY(rowIndex); // получаем значение y дл€ данной строки
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            function.setY(rowIndex, (double) value); // устанавливаем новое значение y дл€ данной строки
            fireTableCellUpdated(rowIndex, columnIndex); // обновл€ем €чейку
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1; // только €чейки столбца y €вл€ютс€ редактируемыми
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
