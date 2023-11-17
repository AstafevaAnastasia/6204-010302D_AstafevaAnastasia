package io;
import java.io.*;
import packFunctions.ArrayTabulatedFunction;
import packFunctions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class ArrayTabulatedFunctionSerialization {

    public static void main(String[] args) throws IOException {
        String filePath = "Project2/output/serialized array functions.bin";
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            ArrayTabulatedFunction arrayFunction = createArrayTabulatedFunction();
            TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();
            TabulatedFunction firstDerivative = differentialOperator .derive(arrayFunction);
            TabulatedFunction secondDerivative = differentialOperator .derive(firstDerivative);

            io.FunctionsIO.serialize(outputStream, arrayFunction);
            io.FunctionsIO.serialize(outputStream, firstDerivative);
            io.FunctionsIO.serialize(outputStream, secondDerivative);

            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            TabulatedFunction deserializedFunction1 = (TabulatedFunction) FunctionsIO.deserialize(inputStream);
            System.out.println("ArrayTabulatedFunction: " + deserializedFunction1);
        }

        try (BufferedInputStream inputStream2 = new BufferedInputStream(new FileInputStream(filePath))) {
            TabulatedFunction deserializedFunction2 = (TabulatedFunction) FunctionsIO.deserialize(inputStream2);
            System.out.println("First derivative: " + deserializedFunction2);
        }

        try (BufferedInputStream inputStream3 = new BufferedInputStream(new FileInputStream(filePath))) {
            TabulatedFunction deserializedFunction3 = (TabulatedFunction) FunctionsIO.deserialize(inputStream3);
            System.out.println("Second derivative: " + deserializedFunction3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayTabulatedFunction createArrayTabulatedFunction() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 2.0, 3.0};
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}