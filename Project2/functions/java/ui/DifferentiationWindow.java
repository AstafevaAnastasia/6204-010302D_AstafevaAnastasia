package ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

import packFunctions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

public class DifferentiationWindow extends JFrame {
    private TabulatedFunction initialFunction;
    private TabulatedFunction differentiatedFunction;
    private TabulatedDifferentialOperator tabulatedDifferentialOperator;
    double[] xValues;
    double[] yValues;
    public DifferentiationWindow() {

        setTitle("�����������������");
        setSize(600, 400);

        // �������� ����������� ����
        JPanel panel = new JPanel(new FlowLayout());

        JButton createButton = new JButton("�������");
        panel.add(createButton);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TabulatedFunctionUI(xValues, yValues);
                initialFunction = (TabulatedFunction)TabulatedFunctionDatabase.database;

                Object[][] tableData = new Object[initialFunction.getCount()][2];
                for (int i = 0; i < initialFunction.getCount(); i++) {
                    tableData[i][0] = initialFunction.getX(i);
                    tableData[i][1] = initialFunction.getY(i);
                }

                // �������� ���������� ��������
                String[] columnNames = {"X", "Y"};

                // �������� JTable � ������� � �����������
                JTable table = new JTable(tableData, columnNames);

                // �������� ������ ��������� ��� �������, ���� ���������� ����� ������, ��� ����� ������������ �� ������
                JScrollPane scrollPane = new JScrollPane(table);

                // ���������� ������ ��������� �� �����
                add(scrollPane);
            }
        });



        JButton differentiateButton = new JButton("����������������");
        panel.add(differentiateButton);
        differentiateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ���������� �����������������
                differentiatedFunction = tabulatedDifferentialOperator.deriveSynchronously((TabulatedFunction)initialFunction);
                JLabel diffFunctionLabel = new JLabel("������������������ �������: ");
                add(diffFunctionLabel);
                // �������� ���������� ������� ��� �������� ������ �������
                Object[][] tableData = new Object[differentiatedFunction.getCount()][2];
                for (int i = 0; i < differentiatedFunction.getCount(); i++) {
                    tableData[i][0] = differentiatedFunction.getX(i);
                    tableData[i][1] = differentiatedFunction.getY(i);
                }

                // �������� ���������� ��������
                String[] columnNames = {"X", "Y"};

                // �������� JTable � ������� � �����������
                JTable table = new JTable(tableData, columnNames);

                // �������� ������ ��������� ��� �������, ���� ���������� ����� ������, ��� ����� ������������ �� ������
                JScrollPane scrollPane = new JScrollPane(table);

                // ���������� ������ ��������� �� �����
                add(scrollPane);
            }
        });

        // ��������� �������� ������ � ����
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new DifferentiationWindow();
    }
}
