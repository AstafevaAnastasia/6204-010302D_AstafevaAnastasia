package ui;

import packFunctions.*;
import packFunctions.TabulatedFunction;
import packFunctions.factory.*;
import packFunctions.MathFunction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TabulatedFunctionUI extends JFrame {
    private JComboBox<String> functionComboBox;
    private Map<String, MathFunction> functionMap;
    private JTextField intervalTextField;

    private JTextField pointsField = new JTextField();
    private JButton createButton;
    private JTable table;
    private DefaultTableModel tableModel;

    private TabulatedFunctionFactory factory; // ������� ��� �������� �������������� �������

    // ����������� ��� �������� ������� �� �������� x � y
    public TabulatedFunctionUI(double[] xValues, double[] yValues) {
        setTitle("Tabulated Function");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());
        createTable();


        // ���������� ������� ���������� �� �������� x � y
        for (int i = 0; i < xValues.length; i++) {
            xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
            yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
        }

        setVisible(true);
    }

    // ����������� ��� �������� ������� �� ��������� MathFunction
    public TabulatedFunctionUI() {
        setTitle("Tabulated Function Creator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(4, 2));

        // ������������� ����������� ������ �������
        functionComboBox = new JComboBox<>();
        add(new JLabel("Function:"));
        add(functionComboBox);

        // ������������� ���������� ���� ��� ����� ���������� �����
        pointsField = new JTextField();
        add(new JLabel("Points:"));
        add(pointsField);

        // ������������� ���������� ���� ��� ����� ���������
        intervalTextField = new JTextField();
        add(new JLabel("Interval:"));
        add(intervalTextField);

        // ������������� ������ "Create"
        createButton = new JButton("Create");
        add(createButton);

        // ���������� ����������� � ���������� ������� � ��������� MathFunction
        functionMap = new HashMap<>();
        functionMap.put("Identity Function", new IdentityFunction());
        functionMap.put("Sin Function", new CosTwoArgFunction());
        functionMap.put("Cos Function", new CosineFunction());
        functionMap.put("Exp Function", new SqrFunction());
        // �������� ��������� ������� � ����������� functionMap

        // ��������� �������� ������� ��� ����������� � ���������� ������
        String[] functionNames = functionMap.keySet().toArray(new String[0]);
        Arrays.sort(functionNames);
        functionComboBox.setModel(new DefaultComboBoxModel<>(functionNames));

        // ���������� ������� ������� �� ������ "Create"
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createFunction();
            }
        });

        setVisible(true);
    }

    // ����� ��� �������� �������
    private void createTable() {
        // �������� ������ �������
        tableModel = new DefaultTableModel();
        tableModel.addColumn("X");
        tableModel.addColumn("Y");

        int pointsCount = Integer.parseInt(pointsField.getText());
        for (int i = 0; i < pointsCount; i++) {
            tableModel.addRow(new Object[]{"", ""});
        }
        // �������� ������� �� ��������������� �������
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(350, 200));
        JScrollPane scrollPane = new JScrollPane(table);

        // ���������� ������ ��������� � �������� �� �����
        add(scrollPane);
        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(null);
    }

    // ����� ��� �������� ������� �� ������ �������� �� �������
    private void createFunction() {
        // ��������� �������� �� ������� � �������� ������� TabulatedFunction
        int rowCount = tableModel.getRowCount();
        double[] xValues = new double[rowCount];
        double[] yValues = new double[rowCount];

        for (int i = 0; i < rowCount; i++) {
            xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
            yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
        }

        try {
            // �������� �������������� ������� � �������������� �������
            TabulatedFunction tabulatedFunction = factory.create(xValues, yValues);
            dispose(); // �������� �������� ����

            // ��������� ��� ��� ������������� ��������� �������������� �������
            // ...

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // ������ ������������� ������� ������������
         double[] xValues = {};
         double[] yValues = {};
         new TabulatedFunctionUI(xValues, yValues);

        // ������ ������������� ������� ������������
        //new TabulatedFunctionUI();
    }
}