package ui;

import packFunctions.factory.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsDialog extends JDialog {
    private boolean useArrayFactory; // флаг, указывающий на использование фабрики для массива

    public SettingsDialog(JFrame parent, ArrayTabulatedFunctionFactory arrFactory, LinkedListTabulatedFunctionFactory listFactory) {
        super(parent, "Settings", true);
        this.useArrayFactory = true; // по умолчанию используем фабрику для массива

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JRadioButton arrayRadio = new JRadioButton("Array", true);
        arrayRadio.setActionCommand("array");
        arrayRadio.addActionListener(new FactorySelectionListener());

        JRadioButton linkedListRadio = new JRadioButton("Linked List");
        linkedListRadio.setActionCommand("linked_list");
        linkedListRadio.addActionListener(new FactorySelectionListener());

        ButtonGroup group = new ButtonGroup();
        group.add(arrayRadio);
        group.add(linkedListRadio);

        panel.add(arrayRadio);
        panel.add(linkedListRadio);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (useArrayFactory) {
                    ((MainWindow) parent).updateFactory(arrFactory);
                } else {
                    ((MainWindow) parent).updateFactory(listFactory);
                }
                dispose();
            }
        });

        panel.add(saveButton);

        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    private class FactorySelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("array")) {
                useArrayFactory = true; // выбираем фабрику для массива
            } else if (command.equals("linked_list")) {
                useArrayFactory = false; // выбираем фабрику для связного списка
            }
        }
    }

}
