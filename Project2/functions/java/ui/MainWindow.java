package ui;

import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
    private ArrayTabulatedFunctionFactory arrFactory; // объект фабрики для массива
    private LinkedListTabulatedFunctionFactory listFactory; // объект фабрики для связного списка
    private JButton settingsButton;

    public MainWindow() {
        setTitle("Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton openSettingsButton = new JButton("Settings");
        openSettingsButton.addActionListener(e -> openSettingsDialog());

        JPanel panel = new JPanel();
        panel.add(openSettingsButton);
        add(panel);
    }

    private void openSettingsDialog() {
        JDialog settingsDialog = new JDialog(this, "Settings", true);
        settingsDialog.setSize(400, 300);

        JRadioButton arrayButton = new JRadioButton("Array");
        arrayButton.addActionListener(e -> updateFactory(new ArrayTabulatedFunctionFactory()));

        JRadioButton listButton = new JRadioButton("Linked List");
        listButton.addActionListener(e -> updateFactory(new LinkedListTabulatedFunctionFactory()));

        ButtonGroup group = new ButtonGroup();
        group.add(arrayButton);
        group.add(listButton);

        // По умолчанию выбран массив
        arrayButton.setSelected(true);

        // Загрузить предыдущий выбор (например, из настроек или сохраненного файла)

        JButton saveButton = new JButton("Save");
        saveButton.setLocation(100,100);
        saveButton.addActionListener(e -> {
            if (arrayButton.isSelected()) {
                // сохранить выбор массива
            } else if (listButton.isSelected()) {
                // сохранить выбор связного списка
            }
            settingsDialog.dispose();
        });

        settingsDialog.add(arrayButton);
        settingsDialog.add(listButton);
        settingsDialog.add(saveButton);

        settingsDialog.pack();
        settingsDialog.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == settingsButton) {
            openSettingsDialog();
        }
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }

    public void updateFactory(ArrayTabulatedFunctionFactory newFactory) {
        arrFactory = newFactory;
    }

    public void updateFactory(LinkedListTabulatedFunctionFactory newFactory) {
        listFactory = newFactory;
    }

    public ArrayTabulatedFunctionFactory getArrayFactory() {
        return arrFactory;
    }

    public LinkedListTabulatedFunctionFactory getLinkedListFactory() {
        return listFactory;
    }
}
