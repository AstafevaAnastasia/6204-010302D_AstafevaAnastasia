package concurrent;
import packFunctions.*;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        // ������� ������ function ���� TabulatedFunction, ��������� ����������� ������ LinkedListTabulatedFunction
        // � ��������� � ���� ������ ���� ConstantFunction, �������� ������ � ����� ���������, � ����� ���������� ����� �� ���������
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1),1,1000,1000);
        Runnable readTask = new ReadTask(function);
        Runnable writeTask = new WriteTask(function,0.5);
        Thread threadRead = new Thread(readTask);
        Thread threadWrite = new Thread(writeTask);
        threadRead.start(); // ��������� ����� threadRead
        threadWrite.start(); // ��������� ����� threadWrite
    }
}