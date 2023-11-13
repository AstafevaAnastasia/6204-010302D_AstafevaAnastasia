package io;
import java.io.*;

import packFunctions.ArrayTabulatedFunction;
import packFunctions.LinkedListTabulatedFunction;
import packFunctions.TabulatedFunction;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {
        try (BufferedOutputStream arrayOutputStream = new BufferedOutputStream(new FileOutputStream("Project2/output/array function.bin"));
             BufferedOutputStream linkedListOutputStream = new BufferedOutputStream(new FileOutputStream("Project2/output/linked list function.bin"))) {
            // Создаем табулированные функции
            TabulatedFunction arrayFunction = createArrayFunction();
            TabulatedFunction linkedListFunction = createLinkedListFunction();

            // Записываем функции в соответствующие потоки
            FunctionsIO.writeTabulatedFunction(arrayOutputStream, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListOutputStream, linkedListFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static TabulatedFunction createArrayFunction() {
        // Создаем и возвращаем табулированную функцию на основе массива
        TabulatedFunction function = new ArrayTabulatedFunction(new double[] {0.0, 1.0, 2.0, 3.0, 4.0}, new double[] {0.0, 1.0, 4.0, 9.0, 16.0});
        return function;
    }

    private static TabulatedFunction createLinkedListFunction() {
        // Создаем и возвращаем табулированную функцию на основе связного списка
        TabulatedFunction function = new LinkedListTabulatedFunction(new double[] {0.0, 1.0, 2.0, 3.0, 4.0}, new double[] {0.0, 1.0, 4.0, 9.0, 16.0});
        return function;
    }

}