package io;

import operations.TabulatedDifferentialOperator;
import packFunctions.*;
import packFunctions.factory.*;
import java.io.*;

public class TabulatedFunctionFileInputStream {

    public static void main(String[] args) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("Project2/input/binary function.bin"))) {
            TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory(); // �������� ������� ��� ������ � �������������� ��������
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputStream, factory); // ������ �������������� ������� �� �������� ������
            System.out.println(function); // ����� �� ����� ���������� �������
        }
        catch (IOException e) { // ��������� ���������� �����-������
            e.printStackTrace(); // ����� ���������� �� ������
        }
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in)); // �������� ������ ��� ������ ������ � ����������
            System.out.println("������� ������ � �������� �������: "); // ����� ����������� � ����� ������
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory(); // �������� ������� ��� ������ � �������������� ��������
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory); // �������� ��������� �����������������
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputReader, factory); // ������ �������������� ������� �� �������� ������
            System.out.println(operator.derive(function)); // ����� �� ����� ����������� �������
        }
        catch (IOException e) { // ��������� ���������� �����-������
            e.printStackTrace(); // ����� ���������� �� ������
        }
    }
}