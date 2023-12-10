package ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

import packFunctions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

public class DifferentiationWindow extends JFrame {
    private TabulatedFunctionDatabase initialFunction;
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
                //add(differentiatedFunction);
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
