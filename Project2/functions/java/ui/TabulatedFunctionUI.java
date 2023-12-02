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

    private TabulatedFunctionFactory factory; // Фабрика для создания табулированных функций

    // Конструктор для создания таблицы из массивов x и y
    public TabulatedFunctionUI(double[] xValues, double[] yValues) {
        setTitle("Tabulated Function");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());
        createTable();


        // Заполнение таблицы значениями из массивов x и y
        for (int i = 0; i < xValues.length; i++) {
            xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
            yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
        }

        setVisible(true);
    }

    // Конструктор для создания таблицы из выбранной MathFunction
    public TabulatedFunctionUI() {
        setTitle("Tabulated Function Creator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(4, 2));

        // Инициализация выпадающего списка функций
        functionComboBox = new JComboBox<>();
        add(new JLabel("Function:"));
        add(functionComboBox);

        // Инициализация текстового поля для ввода количества точек
        pointsField = new JTextField();
        add(new JLabel("Points:"));
        add(pointsField);

        // Инициализация текстового поля для ввода интервала
        intervalTextField = new JTextField();
        add(new JLabel("Interval:"));
        add(intervalTextField);

        // Инициализация кнопки "Create"
        createButton = new JButton("Create");
        add(createButton);

        // Заполнение отображения с названиями функций и объектами MathFunction
        functionMap = new HashMap<>();
        functionMap.put("Identity Function", new IdentityFunction());
        functionMap.put("Sin Function", new CosTwoArgFunction());
        functionMap.put("Cos Function", new CosineFunction());
        functionMap.put("Exp Function", new SqrFunction());
        // Добавьте остальные функции в отображение functionMap

        // Получение названий функций для отображения в выпадающем списке
        String[] functionNames = functionMap.keySet().toArray(new String[0]);
        Arrays.sort(functionNames);
        functionComboBox.setModel(new DefaultComboBoxModel<>(functionNames));

        // Обработчик события нажатия на кнопку "Create"
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createFunction();
            }
        });

        setVisible(true);
    }

    // Метод для создания таблицы
    private void createTable() {
        // Создание модели таблицы
        tableModel = new DefaultTableModel();
        tableModel.addColumn("X");
        tableModel.addColumn("Y");

        int pointsCount = Integer.parseInt(pointsField.getText());
        for (int i = 0; i < pointsCount; i++) {
            tableModel.addRow(new Object[]{"", ""});
        }
        // Создание таблицы со сгенерированной моделью
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(350, 200));
        JScrollPane scrollPane = new JScrollPane(table);

        // Добавление панели прокрутки с таблицей на форму
        add(scrollPane);
        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(null);
    }

    // Метод для создания функции на основе значений из таблицы
    private void createFunction() {
        // Получение значений из таблицы и создание объекта TabulatedFunction
        int rowCount = tableModel.getRowCount();
        double[] xValues = new double[rowCount];
        double[] yValues = new double[rowCount];

        for (int i = 0; i < rowCount; i++) {
            xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
            yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
        }

        try {
            // Создание табулированной функции с использованием фабрики
            TabulatedFunction tabulatedFunction = factory.create(xValues, yValues);
            dispose(); // Закрытие текущего окна

            // Дополните код для использования созданной табулированной функции
            // ...

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Пример использования первого конструктора
         double[] xValues = {};
         double[] yValues = {};
         new TabulatedFunctionUI(xValues, yValues);

        // Пример использования второго конструктора
        //new TabulatedFunctionUI();
    }
}