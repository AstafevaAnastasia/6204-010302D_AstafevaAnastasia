package io;
import packFunctions.TabulatedFunction;
import java.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import packFunctions.factory.TabulatedFunctionFactory;

public final class FunctionsIO {
    private FunctionsIO() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Instances of FunctionsIO are not allowed");
    }

    public static void writeTabulatedFunction(BufferedOutputStream writer, TabulatedFunction function) throws IOException {
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

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        objectOutputStream.writeObject(function);
        objectOutputStream.flush();
    }
    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        String line = reader.readLine();
        int pointCount = Integer.parseInt(line);

        double[] xValues = new double[pointCount];
        double[] yValues = new double[pointCount];

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));

        for (int i = 0; i < pointCount; i++) {
            line = reader.readLine();
            String[] values = line.split(" ");
            try {
                xValues[i] = numberFormat.parse(values[0]).doubleValue();
                yValues[i] = numberFormat.parse(values[1]).doubleValue();
            } catch (ParseException e) {
                throw new IOException("Error parsing values from the file.", e);
            }
        }

        return factory.create(xValues, yValues);

    }

    public static Object deserialize(BufferedInputStream inputStream) {
        Object obj = null;
        try {
            ObjectInputStream in = new ObjectInputStream(inputStream);
            obj = in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
