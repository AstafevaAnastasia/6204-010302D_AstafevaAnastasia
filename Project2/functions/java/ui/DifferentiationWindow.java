package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import packFunctions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

public class DifferentiationWindow extends JFrame {
    private TabulatedFunctionUI initialFunction;
    private TabulatedFunction differentiatedFunction;
    private TabulatedDifferentialOperator tabulatedDifferentialOperator;

    double[] xValues;
    double[] yValues;

    public DifferentiationWindow() {

        setTitle("Differentiation of a tabulated function");
        setSize(600, 400);

        // �������� ����������� ����
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JPanel initialFunctionPanel = new JPanel();
        JPanel differentiatedFunctionPanel = new JPanel();

        JButton createButton = new JButton("Create");
        panel.add(createButton);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialFunction = new TabulatedFunctionUI(xValues, yValues);

                // �������� ����������� ��� ����������� ��������� �������
                JLabel initialFunctionLabel = new JLabel("Initial Function: ");
                JTextField initialFunctionTextField = new JTextField(20);
                initialFunctionTextField.setText(initialFunction.toString());
                initialFunctionPanel.add(initialFunctionLabel);
                initialFunctionPanel.add(initialFunctionTextField);
            }
        });
        initialFunctionPanel.add(createButton);

        JButton differentiateButton = new JButton("Differentiate");
        differentiateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ���������� �����������������
                differentiatedFunction = tabulatedDifferentialOperator.deriveSynchronously((TabulatedFunction)initialFunction);
                // ���������� ����������� ������������������ �������
                // �������� ����������� ��� ����������� ���������� �����������������
                JLabel diffFunctionLabel = new JLabel("Differentiated Function: ");
                JTextField diffFunctionTextField = new JTextField(20);
                diffFunctionTextField.setText(differentiatedFunction.toString());
                differentiatedFunctionPanel.add(diffFunctionLabel);
                differentiatedFunctionPanel.add(diffFunctionTextField);
            }
        });
        panel.add(differentiateButton);

        // ���������� ������� �� �������� ������ ����
        panel.add(initialFunctionPanel);
        panel.add(differentiatedFunctionPanel);

        // ��������� �������� ������ � ����
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new DifferentiationWindow();
    }
}
