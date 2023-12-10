package ui;

import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;
import packFunctions.factory.TabulatedFunctionFactory;

import javax.swing.*;

public class MainWindow extends JFrame {
    public TabulatedFunctionFactory factory; // ������� ��� �������� �������������� �������
    public ArrayTabulatedFunctionFactory arrFactory; // ������ ������� ��� �������
    public LinkedListTabulatedFunctionFactory listFactory; // ������ ������� ��� �������� ������
    private SettingsDialog settingsDialog; // ���� ��� �������� ���������� ������ SettingsDialog

    public MainWindow() {
        super("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsDialog = new SettingsDialog(this, arrFactory, listFactory); // ������������� ���������� ������ SettingsDialog
        JMenuBar menuBar = new JMenuBar();
        JMenu settingsMenu = new JMenu("Menu");
        JMenuItem openSettingsItem = new JMenuItem("Settings");
        openSettingsItem.addActionListener(e -> openSettingsDialog());
        settingsMenu.add(openSettingsItem);
        menuBar.add(settingsMenu);
        JMenuItem openOperationWindowItem = new JMenuItem("Operation Window");
        openOperationWindowItem.addActionListener(e -> openOperationWindow());
        settingsMenu.add(openOperationWindowItem);
        JMenuItem openDifferentiationWindowItem = new JMenuItem("Differentiation Window");
        openDifferentiationWindowItem.addActionListener(e -> openDifferentiationWindow());
        settingsMenu.add(openDifferentiationWindowItem);

        setJMenuBar(menuBar);

        arrFactory = new ArrayTabulatedFunctionFactory(); // �� ��������� ���������� ������
        listFactory = new LinkedListTabulatedFunctionFactory(); // �� ��������� ���������� ������� ������

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
        this.factory = newFactory;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }

}
