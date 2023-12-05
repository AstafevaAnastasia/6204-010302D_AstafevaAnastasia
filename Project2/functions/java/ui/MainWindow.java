package ui;

import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;

import javax.swing.*;

public class MainWindow extends JFrame {
    private ArrayTabulatedFunctionFactory arrFactory; // объект фабрики для массива
    private LinkedListTabulatedFunctionFactory listFactory; // объект фабрики для связного списка

    public MainWindow() {
        super("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu settingsMenu = new JMenu("Settings");
        JMenuItem openSettingsItem = new JMenuItem("Open Settings");
        openSettingsItem.addActionListener(e -> openSettingsDialog());
        settingsMenu.add(openSettingsItem);
        menuBar.add(settingsMenu);
        setJMenuBar(menuBar);

        arrFactory = new ArrayTabulatedFunctionFactory(); // по умолчанию используем массив
        listFactory = new LinkedListTabulatedFunctionFactory(); // по умолчанию используем связный список

        setSize(400, 300);
        setLocationRelativeTo(null); // отображаем окно по центру экрана
    }

    private void openSettingsDialog() {
        SettingsDialog settingsDialog = new SettingsDialog(this, arrFactory, listFactory);
        settingsDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
