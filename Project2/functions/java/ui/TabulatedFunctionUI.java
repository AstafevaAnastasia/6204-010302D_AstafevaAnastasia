package ui;

import packFunctions.*;
import packFunctions.TabulatedFunction;
import packFunctions.factory.*;
import packFunctions.MathFunction;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Locale;
import java.util.Locale.LanguageRange;
import java.util.Map;
import java.util.TreeMap;

public class TabulatedFunctionUI extends JFrame {
    Locale russianLocale = new Locale("ru", "RU");
    private JComboBox<String> functionComboBox;
    private Map<String, MathFunction> functionMap;
    private JTextField intervalTextField;
    private JTextField pointsField = new JTextField();
    private JButton createButton;
    private JTable table;
    private DefaultTableModel tableModel = new DefaultTableModel();
    TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory(); // ������� ��� �������� �������������� �������

    public static TabulatedFunction tabulatedFunction;
    // ����������� ��� �������� ������� �� �������� x � y
    public TabulatedFunctionUI(double[] xValues, double[] yValues) {
        setTitle("Tabulated Function");
        setSize(400, 300);
        setLayout(new FlowLayout());

        pointsField = new JTextField(10);
        createButton = new JButton("Create");

        createButton.addActionListener(e -> createTable(xValues, yValues));
        add(pointsField);
        add(createButton);

        setVisible(true);
    }

    // ����������� ��� ����, � ������� �������� �������������� �������
    public TabulatedFunctionUI() {
        functionMap = new TreeMap<>();
        functionMap.put("Квадратичная функция", new SqrFunction());
        functionMap.put("Тождественная функция", new IdentityFunction());
        functionMap.put("Косинус двойного угла", new CosTwoArgFunction());
        functionMap.put("Косинус", new CosineFunction());

        setTitle("TabulatedFunction");
        setSize(400, 300);

        // ������� ���������� ����������
        functionComboBox = new JComboBox<>(functionMap.keySet().toArray(new String[0]));
        pointsField = new JTextField();
        intervalTextField = new JTextField();
        JButton createButton = new JButton("done");

        // ������������� ����������
        setLayout(new GridLayout(4, 2));
        add(new JLabel("func:"));
        add(functionComboBox);
        add(new JLabel("points:"));
        add(pointsField);
        add(new JLabel("interval (start-end):"));
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
                tabulatedFunction = factory.createTabulatedFunction(selectedFunction, start, end, points);

                // ������ �� ������� ��� �������� ������
                System.out.println("табулированная функция: " + tabulatedFunction);

                // ��������� ����
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TabulatedFunctionUI.this,
                        "Ошибка ", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(TabulatedFunctionUI.this,
                        "Ошибка: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // ����� ��� �������� �������
    private void createTable(double[] xValues, double[] yValues) {
        try {
            int pointsCount = Integer.parseInt(pointsField.getText());
            if (pointsCount < 0) throw new IllegalArgumentException("Ошибка");

            tableModel = new DefaultTableModel();
            tableModel.addColumn("X");
            tableModel.addColumn("Y");

            for (int i = 0; i < pointsCount; i++) tableModel.addRow(new Object[]{"", ""});

            // �������� �������
            table = new JTable(tableModel);
            table.setPreferredScrollableViewportSize(new Dimension(350, 200));
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(300, 200));

            createButton.setText("done");
            createButton.addActionListener(e -> {
                System.out.println("Cool");
                createFunction(xValues, yValues);
                dispose(); // �������� ���� ����� �������� ������
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
                try {
                    xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                    System.out.println(xValues[i] + " " + i);
                    yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
                    System.out.println(yValues[i] + " " + i);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }

            try {
                // �������� �������������� ������� � �������������� �������
                tabulatedFunction = factory.create(xValues, yValues);
                System.out.println("табулированная функция " + tabulatedFunction);
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    public static TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }

    public static void main(String[] args) {
        // ������ ������������� ������� ������������
         double[] xValues = {};
         double[] yValues = {};
         new TabulatedFunctionUI(xValues, yValues);
        // ������ ������������� ������� ������������
         new TabulatedFunctionUI();

    }
}