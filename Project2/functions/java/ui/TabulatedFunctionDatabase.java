package ui;

import packFunctions.TabulatedFunction;
import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;
import packFunctions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionDatabase {
    private Node head;
    private int size;
    public static TabulatedFunctionDatabase database;

    public static TabulatedFunctionFactory factory; // ������� ��� �������� �������������� �������
    public static ArrayTabulatedFunctionFactory arrFactory; // ������ ������� ��� �������
    public static LinkedListTabulatedFunctionFactory listFactory; // ������ ������� ��� �������� ������

    public static class Node {
        TabulatedFunction tabulatedFunction;
        Node next;
        Node prev;

        public Node(TabulatedFunction tabulatedFunction){this.tabulatedFunction = tabulatedFunction;}
        public String toString() {
            return (tabulatedFunction.getClass().getSimpleName() + " ������: " + tabulatedFunction.getCount()
                    + " (" + tabulatedFunction.leftBound() + ";" + tabulatedFunction.rightBound() + ")");
        }
    }

    public void addFunction(TabulatedFunction function) {
        Node newNode = new Node(function);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
        size++;
    }


    public int getSize(){return size;}
}
