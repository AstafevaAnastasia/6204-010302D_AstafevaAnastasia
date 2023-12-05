package ui;

import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;

import javax.swing.*;

public class MainWindow extends JFrame {
    private ArrayTabulatedFunctionFactory arrFactory; // ������ ������� ��� �������
    private LinkedListTabulatedFunctionFactory listFactory; // ������ ������� ��� �������� ������

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

        arrFactory = new ArrayTabulatedFunctionFactory(); // �� ��������� ���������� ������
        listFactory = new LinkedListTabulatedFunctionFactory(); // �� ��������� ���������� ������� ������

        setSize(400, 300);
        setLocationRelativeTo(null); // ���������� ���� �� ������ ������
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
