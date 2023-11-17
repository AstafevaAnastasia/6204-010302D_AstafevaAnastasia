package io;

import packFunctions.ArrayTabulatedFunction;
import packFunctions.LinkedListTabulatedFunction;
import packFunctions.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWrite {
    public static void main(String[] args) {

        try (BufferedWriter arrayWriter = new BufferedWriter(new FileWriter("Project2/output/array function.txt"));
             BufferedWriter linkedWriter = new BufferedWriter(new FileWriter("Project2/output/linked list function.txt"))) {

            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(new double[]{0, 1, 2, 3}, new double[]{0, 1, 4, 9});
            TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(new double[]{0, 1, 2, 3}, new double[]{0, 1, 4, 9});

            FunctionsIO.writeTabulatedFunction(arrayWriter, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedWriter, linkedListFunction);

            System.out.println("Success");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}