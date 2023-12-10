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

        setTitle("Дифференцирование");
        setSize(600, 400);

        // Создание компонентов окна
        JPanel panel = new JPanel(new FlowLayout());

        JButton createButton = new JButton("Создать");
        panel.add(createButton);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TabulatedFunctionUI(xValues, yValues);
            }
        });



        JButton differentiateButton = new JButton("Дифференцировать");
        panel.add(differentiateButton);
        differentiateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Выполнение дифференцирования
                differentiatedFunction = tabulatedDifferentialOperator.deriveSynchronously((TabulatedFunction)initialFunction);
                JLabel diffFunctionLabel = new JLabel("Дифференцированная функция: ");
                add(diffFunctionLabel);
                //add(differentiatedFunction);
            }
        });

        // Установка основной панели в окне
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new DifferentiationWindow();
    }
}
