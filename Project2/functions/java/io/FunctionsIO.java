package io;
import packFunctions.TabulatedFunction;
import java.io.*;
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

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        int pointCount = function.getCount();
        // Записываем количество точек
        dataOutputStream.writeInt(pointCount);
        // Записываем значения всех точек
        for (int i = 0; i < pointCount; i++) {
            double x = function.getX(i);
            double y = function.getY(i);
            dataOutputStream.writeDouble(x);
            dataOutputStream.writeDouble(y);
        }
        dataOutputStream.flush();
    }

}
