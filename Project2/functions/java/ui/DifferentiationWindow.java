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
                initialFunction = (TabulatedFunction)TabulatedFunctionDatabase.database;

                Object[][] tableData = new Object[initialFunction.getCount()][2];
                for (int i = 0; i < initialFunction.getCount(); i++) {
                    tableData[i][0] = initialFunction.getX(i);
                    tableData[i][1] = initialFunction.getY(i);
                }

                // Создание заголовков столбцов
                String[] columnNames = {"X", "Y"};

                // Создание JTable с данными и заголовками
                JTable table = new JTable(tableData, columnNames);

                // Создание панели прокрутки для таблицы, если количество строк больше, чем может отобразиться на экране
                JScrollPane scrollPane = new JScrollPane(table);

                // Добавление панели прокрутки на экран
                add(scrollPane);
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
                // Создание двумерного массива для хранения данных таблицы
                Object[][] tableData = new Object[differentiatedFunction.getCount()][2];
                for (int i = 0; i < differentiatedFunction.getCount(); i++) {
                    tableData[i][0] = differentiatedFunction.getX(i);
                    tableData[i][1] = differentiatedFunction.getY(i);
                }

                // Создание заголовков столбцов
                String[] columnNames = {"X", "Y"};

                // Создание JTable с данными и заголовками
                JTable table = new JTable(tableData, columnNames);

                // Создание панели прокрутки для таблицы, если количество строк больше, чем может отобразиться на экране
                JScrollPane scrollPane = new JScrollPane(table);

                // Добавление панели прокрутки на экран
                add(scrollPane);
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
