package ui;

import packFunctions.*;
import packFunctions.TabulatedFunction;
import packFunctions.factory.*;
import packFunctions.MathFunction;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class TabulatedFunctionUI extends JFrame {
    private JComboBox<String> functionComboBox;
    private Map<String, MathFunction> functionMap;
    private JTextField intervalTextField;
    private JTextField pointsField = new JTextField();
    private JButton createButton;
    private JTable table;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory(); // ������� ��� �������� �������������� �������

    // ����������� ��� �������� ������� �� �������� x � y
    public TabulatedFunctionUI(double[] xValues, double[] yValues) {
        setTitle("Tabulated Function");
        setSize(400, 300);
        setLayout(new FlowLayout());

        pointsField = new JTextField(10);
        createButton = new JButton("�������");

        createButton.addActionListener(e -> createTable(xValues, yValues));
        add(pointsField);
        add(createButton);

        setVisible(true);
    }

    // ����������� ��� ����, � ������� �������� �������������� �������
    public TabulatedFunctionUI() {
        functionMap = new TreeMap<>();
        functionMap.put("������������ �������", new SqrFunction());
        functionMap.put("������������� �������", new IdentityFunction());
        functionMap.put("������� �������� ���������", new CosTwoArgFunction());
        functionMap.put("�������", new CosineFunction());

        setTitle("�������� �������������� �������");
        setSize(400, 300);

        // ������� ���������� ����������
        functionComboBox = new JComboBox<>(functionMap.keySet().toArray(new String[0]));
        pointsField = new JTextField();
        intervalTextField = new JTextField();
        JButton createButton = new JButton("�������");

        // ������������� ����������
        setLayout(new GridLayout(4, 2));
        add(new JLabel("�������:"));
        add(functionComboBox);
        add(new JLabel("���������� �����:"));
        add(pointsField);
        add(new JLabel("�������� (������-�����):"));
        add(intervalTextField);
        add(new JLabel()); // ������ ����� ��� ������������ ������
        add(createButton);

        setVisible(true);
        createButton.addActionListener(e -> {
            try {
                // �������� ��������� �������
                String selectedFunctionName = functionComboBox.getSelectedItem().toString();
                MathFunction selectedFunction = functionMap.get(selectedFunctionName);

                // �������� �������� �� ��������� �����
                int points = Integer.parseInt(pointsField.getText());
                String intervalText = intervalTextField.getText();
                String[] intervalParts = intervalText.split("-");
                double start, end;

                if (intervalParts.length == 2) {
                    start = Double.parseDouble(intervalParts[0]);
                    end = Double.parseDouble(intervalParts[1]);
                } else throw new Exception();

                // ������� �������������� ������� � �������������� �������
                TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                TabulatedFunction tabulatedFunction = factory.createTabulatedFunction(selectedFunction, start, end, points);

                // ������ �� ������� ��� �������� ������
                System.out.println("������� �������������� �������: " + tabulatedFunction);

                // ��������� ����
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TabulatedFunctionUI.this,
                        "������������ ��������", "������", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(TabulatedFunctionUI.this,
                        "������: " + ex.getMessage(), "������", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // ����� ��� �������� �������
    private void createTable(double[] xValues, double[] yValues) {
        try {
            int pointsCount = Integer.parseInt(pointsField.getText());
            if (pointsCount < 0) throw new IllegalArgumentException("������������ ��������");

            tableModel = new DefaultTableModel();
            tableModel.addColumn("X");
            tableModel.addColumn("Y");

            for (int i = 0; i < pointsCount; i++) tableModel.addRow(new Object[]{"", ""});

            // �������� �������
            table = new JTable(tableModel);
            table.setPreferredScrollableViewportSize(new Dimension(350, 200));
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(300, 200));

            createButton.setText("�������� �������");
            createButton.addActionListener(e -> {
                try {
                    createFunction(xValues, yValues);
                    dispose(); // �������� ���� ����� �������� �������
                } catch (Exception ex) {
                    ExceptionHandler.handleException(ex);
                }
            });

            add(scrollPane);
            revalidate();
            repaint();
            pack();
            setLocationRelativeTo(null);
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }
    // ����� ��� �������� ������� �� ������ �������� �� �������
    private void createFunction(double[] xValues, double[] yValues) {
        // ��������� �������� �� ������� � �������� ������� TabulatedFunction
        try {
            int rowCount = tableModel.getRowCount();
            xValues = new double[rowCount];
            yValues = new double[rowCount];

            for (int i = 0; i < rowCount; i++) {
                xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            }

            try {
                // �������� �������������� ������� � �������������� �������
                TabulatedFunction tabulatedFunction = factory.create(xValues, yValues);
                System.out.println("�������������� ������� " + tabulatedFunction);
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    public static void main(String[] args) {
        // ������ ������������� ������� ������������
        // double[] xValues = {};
         //double[] yValues = {};
         //new TabulatedFunctionUI(xValues, yValues);

        // ������ ������������� ������� ������������
         new TabulatedFunctionUI();
    }
}