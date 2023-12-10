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
    private TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory(); // Фабрика для создания табулированных функций

    // Конструктор для создания таблицы из массивов x и y
    public TabulatedFunctionUI(double[] xValues, double[] yValues) {
        setTitle("Tabulated Function");
        setSize(400, 300);
        setLayout(new FlowLayout());

        pointsField = new JTextField(10);
        createButton = new JButton("Создать");

        createButton.addActionListener(e -> createTable(xValues, yValues));
        add(pointsField);
        add(createButton);

        setVisible(true);
    }

    // конструктор для окна, в котором задается математическая функция
    public TabulatedFunctionUI() {
        functionMap = new TreeMap<>();
        functionMap.put("Квадратичная функция", new SqrFunction());
        functionMap.put("Тождественная функция", new IdentityFunction());
        functionMap.put("Косинус двойного аргумента", new CosTwoArgFunction());
        functionMap.put("Косинус", new CosineFunction());

        setTitle("Создание табулированной функции");
        setSize(400, 300);

        // Создаем компоненты интерфейса
        functionComboBox = new JComboBox<>(functionMap.keySet().toArray(new String[0]));
        pointsField = new JTextField();
        intervalTextField = new JTextField();
        JButton createButton = new JButton("Создать");

        // Устанавливаем компоновку
        setLayout(new GridLayout(4, 2));
        add(new JLabel("Функция:"));
        add(functionComboBox);
        add(new JLabel("Количество точек:"));
        add(pointsField);
        add(new JLabel("Интервал (начало-конец):"));
        add(intervalTextField);
        add(new JLabel()); // Пустая метка для выравнивания кнопки
        add(createButton);

        setVisible(true);
        createButton.addActionListener(e -> {
            try {
                // Получаем выбранную функцию
                String selectedFunctionName = functionComboBox.getSelectedItem().toString();
                MathFunction selectedFunction = functionMap.get(selectedFunctionName);

                // Получаем значения из текстовых полей
                int points = Integer.parseInt(pointsField.getText());
                String intervalText = intervalTextField.getText();
                String[] intervalParts = intervalText.split("-");
                double start, end;

                if (intervalParts.length == 2) {
                    start = Double.parseDouble(intervalParts[0]);
                    end = Double.parseDouble(intervalParts[1]);
                } else throw new Exception();

                // Создаем табулированную функцию с использованием фабрики
                TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                TabulatedFunction tabulatedFunction = factory.createTabulatedFunction(selectedFunction, start, end, points);

                // вывожу на консоль для проверки работы
                System.out.println("Создана табулированная функция: " + tabulatedFunction);

                // Закрываем окно
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TabulatedFunctionUI.this,
                        "Некорректные значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(TabulatedFunctionUI.this,
                        "Ошибка: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Метод для создания таблицы
    private void createTable(double[] xValues, double[] yValues) {
        try {
            int pointsCount = Integer.parseInt(pointsField.getText());
            if (pointsCount < 0) throw new IllegalArgumentException("Некорректное значение");

            tableModel = new DefaultTableModel();
            tableModel.addColumn("X");
            tableModel.addColumn("Y");

            for (int i = 0; i < pointsCount; i++) tableModel.addRow(new Object[]{"", ""});

            // Создание таблицы
            table = new JTable(tableModel);
            table.setPreferredScrollableViewportSize(new Dimension(350, 200));
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(300, 200));

            createButton.setText("Значения введены");
            createButton.addActionListener(e -> {
                try {
                    createFunction(xValues, yValues);
                    dispose(); // Закрытие окна после создания функции
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
    // Метод для создания функции на основе значений из таблицы
    private void createFunction(double[] xValues, double[] yValues) {
        // Получение значений из таблицы и создание объекта TabulatedFunction
        try {
            int rowCount = tableModel.getRowCount();
            xValues = new double[rowCount];
            yValues = new double[rowCount];

            for (int i = 0; i < rowCount; i++) {
                xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            }

            try {
                // Создание табулированной функции с использованием фабрики
                TabulatedFunction tabulatedFunction = factory.create(xValues, yValues);
                System.out.println("Табулированная функция " + tabulatedFunction);
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    public static void main(String[] args) {
        // пример использования первого конструктора
        // double[] xValues = {};
         //double[] yValues = {};
         //new TabulatedFunctionUI(xValues, yValues);

        // Пример использования второго конструктора
         new TabulatedFunctionUI();
    }
}