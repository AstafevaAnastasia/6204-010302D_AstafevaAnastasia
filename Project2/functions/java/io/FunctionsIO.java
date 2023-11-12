package io;
import packFunctions.TabulatedFunction;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import packFunctions.factory.TabulatedFunctionFactory;
public final class FunctionsIO {
    private FunctionsIO() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Instances of FunctionsIO are not allowed");
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) throws IOException {
        int pointCount = function.getCount();
        PrintWriter printWriter = new PrintWriter(writer);

        // Записываем количество точек
        printWriter.println(pointCount);

        // Записываем значения всех точек
        for (int i = 0; i < pointCount; i++) {
            double x = function.getX(i);
            double y = function.getY(i);
            printWriter.printf("%f %f%n", x, y);
        }

        printWriter.flush();
    }

}
