package io;

import operations.TabulatedDifferentialOperator;
import packFunctions.*;
import packFunctions.factory.*;
import java.io.*;

public class TabulatedFunctionFileInputStream {

    public static void main(String[] args) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("Project2/input/binary function.bin"))) {
            TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory(); // создание фабрики для работы с табулированной функцией
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputStream, factory); // чтение табулированной функции из входного потока
            System.out.println(function); // вывод на экран полученной функции
        }
        catch (IOException e) { // обработка исключения ввода-вывода
            e.printStackTrace(); // вывод информации об ошибке
        }
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in)); // создание потока для чтения данных с клавиатуры
            System.out.println("Введите размер и значения функции: "); // вывод приглашения к вводу данных
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory(); // создание фабрики для работы с табулированной функцией
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory); // создание оператора дифференцирования
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputReader, factory); // чтение табулированной функции из входного потока
            System.out.println(operator.derive(function)); // вывод на экран производной функции
        }
        catch (IOException e) { // обработка исключения ввода-вывода
            e.printStackTrace(); // вывод информации об ошибке
        }
    }
}