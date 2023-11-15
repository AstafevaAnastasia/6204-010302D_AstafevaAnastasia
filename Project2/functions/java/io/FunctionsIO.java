package io;
import packFunctions.TabulatedFunction;
import java.io.*;
import packFunctions.factory.TabulatedFunctionFactory;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
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

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        // Создаем DataInputStream для чтения данных из inputStream
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        // Читаем из потока длину массивов xValues и yValues
        int length = dataInputStream.readInt();
        // Создаем массивы для хранения x и y значений
        double[] xValues = new double[length];
        double[] yValues = new double[length];
        // Читаем из потока и заполняем массивы xValues и yValues
        for (int i = 0; i < length; i++) {
            xValues[i] = dataInputStream.readDouble();
            yValues[i] = dataInputStream.readDouble();
        }
        // Используем фабрику для создания объекта TabulatedFunction на основе считанных значений
        return factory.create(xValues, yValues);
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

}
