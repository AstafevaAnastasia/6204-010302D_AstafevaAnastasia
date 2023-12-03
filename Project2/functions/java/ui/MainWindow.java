package ui;

import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
    private ArrayTabulatedFunctionFactory arrFactory; // ������ ������� ��� �������
    private LinkedListTabulatedFunctionFactory listFactory; // ������ ������� ��� �������� ������
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

        // �� ��������� ������ ������
        arrayButton.setSelected(true);

        // ��������� ���������� ����� (��������, �� �������� ��� ������������ �����)

        JButton saveButton = new JButton("Save");
        saveButton.setLocation(100,100);
        saveButton.addActionListener(e -> {
            if (arrayButton.isSelected()) {
                // ��������� ����� �������
            } else if (listButton.isSelected()) {
                // ��������� ����� �������� ������
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
