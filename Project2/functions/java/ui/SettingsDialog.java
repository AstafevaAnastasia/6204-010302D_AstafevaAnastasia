package ui;

import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsDialog extends JDialog {
    private ArrayTabulatedFunctionFactory arrFactory; // объект фабрики дл€ массива
    private LinkedListTabulatedFunctionFactory listFactory; // объект фабрики дл€ св€зного списка
    private boolean useArrayFactory; // флаг, указывающий на использование фабрики дл€ массива

    public SettingsDialog(JFrame parent, ArrayTabulatedFunctionFactory arrFactory, LinkedListTabulatedFunctionFactory listFactory) {
        super(parent, "Settings", true);
        this.arrFactory = arrFactory;
        this.listFactory = listFactory;
        this.useArrayFactory = true; // по умолчанию используем фабрику дл€ массива

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JRadioButton arrayRadio = new JRadioButton("Array Factory", true);
        arrayRadio.setActionCommand("array");
        arrayRadio.addActionListener(new FactorySelectionListener());

        JRadioButton linkedListRadio = new JRadioButton("Linked List Factory");
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
                    ((MainWindow) parent).updateArrayFactory(arrFactory);
                } else {
                    ((MainWindow) parent).updateListFactory(listFactory);
                }
                dispose();
            }
        });

        panel.add(saveButton);

        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }


    public ArrayTabulatedFunctionFactory getArrayFactory() {
        return arrFactory;
    }

    public LinkedListTabulatedFunctionFactory getListFactory() {
        return listFactory;
    }

    private class FactorySelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("array")) {
                useArrayFactory = true; // выбираем фабрику дл€ массива
            } else if (command.equals("linked_list")) {
                useArrayFactory = false; // выбираем фабрику дл€ св€зного списка
            }
        }
    }

}
