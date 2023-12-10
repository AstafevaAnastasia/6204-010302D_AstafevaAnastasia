package ui;

import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;
import packFunctions.factory.TabulatedFunctionFactory;

import javax.swing.*;

public class MainWindow extends JFrame {
    private SettingsDialog settingsDialog; // ���� ��� �������� ���������� ������ SettingsDialog

    public MainWindow() {
        super("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsDialog = new SettingsDialog(this, TabulatedFunctionDatabase.arrFactory, TabulatedFunctionDatabase.listFactory); // ������������� ���������� ������ SettingsDialog
        JMenuBar menuBar = new JMenuBar();
        JMenu choiceMenu = new JMenu("Menu");

        JMenuItem openSettingsItem = new JMenuItem("Settings");
        openSettingsItem.addActionListener(e -> openSettingsDialog());
        choiceMenu.add(openSettingsItem);
        menuBar.add(choiceMenu);

        JMenuItem openOperationWindowItem = new JMenuItem("Operation Window");
        openOperationWindowItem.addActionListener(e -> openOperationWindow());
        choiceMenu.add(openOperationWindowItem);

        JMenuItem openDifferentiationWindowItem = new JMenuItem("Differentiation Window");
        openDifferentiationWindowItem.addActionListener(e -> openDifferentiationWindow());
        choiceMenu.add(openDifferentiationWindowItem);

        setJMenuBar(menuBar);

        TabulatedFunctionDatabase.arrFactory = new ArrayTabulatedFunctionFactory(); // �� ��������� ���������� ������
        TabulatedFunctionDatabase.listFactory = new LinkedListTabulatedFunctionFactory(); // �� ��������� ���������� ������� ������

        setSize(400, 300);
        setLocationRelativeTo(null); // ���������� ���� �� ������ ������
    }

    private void openSettingsDialog() {
        settingsDialog.setVisible(true);
    }

    private void openOperationWindow() {
        TabulatedFunctionOperationWindow operationWindow = new TabulatedFunctionOperationWindow(this);
        operationWindow.setVisible(true);
    }

    private void openDifferentiationWindow() {
        DifferentiationWindow differentiationWindow = new DifferentiationWindow();
        differentiationWindow.setVisible(true);
    }

    public void updateFactory(TabulatedFunctionFactory newFactory) {
        TabulatedFunctionDatabase.factory = newFactory;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }

}
