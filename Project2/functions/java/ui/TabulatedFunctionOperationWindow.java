package ui;

import io.*;
import operations.*;
import packFunctions.ArrayTabulatedFunction;
import packFunctions.TabulatedFunction;
import packFunctions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TabulatedFunctionOperationWindow extends JFrame {
    private TabulatedFunctionOperationService operationService;

    public JTable function1Table;
    public JTable function2Table;
    public JTable resultTable;

    private TabulatedFunctionUI tabulatedFunctionUI;
    private TabulatedFunction firstFunction;
    private TabulatedFunctionFactory firstFunctionFactory;
    private TabulatedFunction secondFunction;
    private TabulatedFunctionFactory secondFunctionFactory;
    private TabulatedFunction resultFunction;

    public TabulatedFunctionOperationWindow(JFrame parent) {
        operationService = new TabulatedFunctionOperationService();

        setTitle("Tabulated Function Operation");
        setSize(800, 400);

        JPanel panel = new JPanel(new GridLayout(6, 3));

        function1Table = new JTable();
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("X");
        model1.addColumn("Y");
        function1Table.setModel(model1);

        function2Table = new JTable();
        DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("X");
        model2.addColumn("Y");
        function2Table.setModel(model2);

        resultTable = new JTable();
        DefaultTableModel model3 = new DefaultTableModel();
        model3.addColumn("X");
        model3.addColumn("Y");
        resultTable.setModel(model3);

        JButton createButton1 = new JButton("Create First Function with arrays");
        JButton createButton3 = new JButton("Create First Function with mathfunc");
        JButton loadButton1 = new JButton("Load First Function");
        JButton saveButton1 = new JButton("Save First Function");

        JButton createButton2 = new JButton("Create Second Function with arrays");
        JButton createButton4 = new JButton("Create Second Function with mathfunc");
        JButton loadButton2 = new JButton("Load Second Function");
        JButton saveButton2 = new JButton("Save Second Function");

        createButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = model1.getRowCount();
                double[] xValues = new double[rowCount];
                double[] yValues = new double[rowCount];
                for (int i = 0; i < rowCount; i++) {
                    xValues[i] = Double.parseDouble(model1.getValueAt(i, 0).toString());
                    yValues[i] = Double.parseDouble(model1.getValueAt(i, 1).toString());
                }
                tabulatedFunctionUI = new TabulatedFunctionUI(xValues, yValues);
                System.out.println(firstFunction);
            }
        });

        createButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabulatedFunctionUI dialog = new TabulatedFunctionUI();
                dialog.setVisible(true);
                System.out.println(firstFunction);
            }
        });

        saveButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                        io.FunctionsIO.serialize(outputStream, (TabulatedFunction) function1Table);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        createButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = model2.getRowCount();
                double[] xValues = new double[rowCount];
                double[] yValues = new double[rowCount];
                for (int i = 0; i < rowCount; i++) {
                    xValues[i] = Double.parseDouble(model2.getValueAt(i, 0).toString());
                    yValues[i] = Double.parseDouble(model2.getValueAt(i, 1).toString());
                }
                tabulatedFunctionUI = new TabulatedFunctionUI(xValues, yValues);
                System.out.println(secondFunction);

            }
        });

        createButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabulatedFunctionUI dialog = new TabulatedFunctionUI();
                dialog.setVisible(true);
            }
        });

        saveButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                        FunctionsIO.serialize(outputStream, (TabulatedFunction) function2Table);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });



        JButton addButton = new JButton("Add");
        JButton subtractButton = new JButton("Subtract");
        JButton multiplyButton = new JButton("Multiply");
        JButton divideButton = new JButton("Divide");
        JButton saveResultButton = new JButton("Save Result");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultFunction = operationService.add(firstFunction, secondFunction);
                System.out.println(resultFunction);
            }
        });

        loadButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        GridLayout layout = new GridLayout(6,3, 1, 1);
        panel.add(new JScrollPane(function1Table));
        panel.add(new JScrollPane(function2Table));
        panel.add(new JScrollPane(resultTable));

        panel.add(createButton1);
      //  panel.add(createButton3);
        panel.add(createButton2);
       // panel.add(createButton4);
        panel.add(addButton);

        panel.add(createButton3);
        panel.add(createButton4);
      //  panel.add(loadButton1);
        //panel.add(loadButton2);
        panel.add(subtractButton);

        panel.add(loadButton1);
        panel.add(loadButton2);
       // panel.add(saveButton1);
        //panel.add(saveButton2);
        panel.add(multiplyButton);

        panel.add(saveButton1);
        panel.add(saveButton2);
        panel.add(divideButton);

        createButton1.setLayout(layout);
        createButton3.setLayout(layout);
        loadButton1.setLayout(layout);
        saveButton1.setLayout(layout);

        createButton2.setLayout(layout);
        createButton4.setLayout(layout);
        loadButton2.setLayout(layout);
        saveButton2.setLayout(layout);

        // panel.add(divideButton);
        panel.add(saveResultButton);

        add(panel);
        // Отображаем форму
        setVisible(true);
        setLocationRelativeTo(parent); // отображаем окно по центру экрана
    }
}
