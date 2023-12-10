package ui;

import packFunctions.TabulatedFunction;
import packFunctions.factory.ArrayTabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;
import packFunctions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionDatabase {
    private Node head;
    private int size;

    public static TabulatedFunctionFactory factory; // Фабрика для создания табулированных функций
    public static ArrayTabulatedFunctionFactory arrFactory; // объект фабрики для массива
    public static LinkedListTabulatedFunctionFactory listFactory; // объект фабрики для связного списка

    public static class Node {
        TabulatedFunction tabulatedFunction;
        Node next;
        Node prev;

        public Node(TabulatedFunction tabulatedFunction){this.tabulatedFunction = tabulatedFunction;}
        public String toString() {
            return (tabulatedFunction.getClass().getSimpleName() + " размер: " + tabulatedFunction.getCount()
                    + " (" + tabulatedFunction.leftBound() + ";" + tabulatedFunction.rightBound() + ")");
        }
    }

    public int getSize(){return size;}
}
