package io;
import packFunctions.TabulatedFunction;
import packFunctions.factory.TabulatedFunctionFactory;
import packFunctions.factory.LinkedListTabulatedFunctionFactory;
import packFunctions.factory.ArrayTabulatedFunctionFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        String fileName = "Project2/input/function.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(reader, arrayFactory);
            System.out.println("Array Function: " + arrayFunction.toString());

            reader.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(fileName));

            TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction linkedListFunction = FunctionsIO.readTabulatedFunction(reader1, linkedListFactory);
            System.out.println("Linked List Function: " + linkedListFunction.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
