package io;

import packFunctions.ArrayTabulatedFunction;
import packFunctions.LinkedListTabulatedFunction;
import packFunctions.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileWrite {
    public static void main(String[] args) {

        try (BufferedOutputStream arrayOutputStream = new BufferedOutputStream(new FileOutputStream("Project2/output/array function.txt"));
             BufferedOutputStream linkedListOutputStream = new BufferedOutputStream(new FileOutputStream("Project2/output/linked list function.txt"))) {

            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(new double[]{0, 1, 2, 3}, new double[]{0, 1, 4, 9});
            TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(new double[]{0, 1, 2, 3}, new double[]{0, 1, 4, 9});

            FunctionsIO.writeTabulatedFunction(arrayOutputStream, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListOutputStream, linkedListFunction);

            System.out.println("Функции успешно записаны в файлы.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}