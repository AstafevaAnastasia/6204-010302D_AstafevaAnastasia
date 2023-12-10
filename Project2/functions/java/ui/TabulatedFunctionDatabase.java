package ui;

import packFunctions.TabulatedFunction;
import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;
import packFunctions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionDatabase {
    private Node head;
    private int size;

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

    public int getSize(){return size;}
}
