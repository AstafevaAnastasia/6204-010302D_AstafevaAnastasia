package ui;

import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;

import javax.swing.*;

public class MainWindow extends JFrame {
    private ArrayTabulatedFunctionFactory arrFactory; // ������ ������� ��� �������
    private LinkedListTabulatedFunctionFactory listFactory; // ������ ������� ��� �������� ������
    private SettingsDialog settingsDialog; // ���� ��� �������� ���������� ������ SettingsDialog

    public MainWindow() {
        super("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsDialog = new SettingsDialog(this, arrFactory, listFactory); // ������������� ���������� ������ SettingsDialog
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
        settingsDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }

    public void updateArrayFactory(ArrayTabulatedFunctionFactory newFactory) {
        this.arrFactory = newFactory;
    }

    public void updateListFactory(LinkedListTabulatedFunctionFactory newFactory) {
        this.listFactory = newFactory;
    }


}
