package concurrent;
import packFunctions.*;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        // создаем объект function типа TabulatedFunction, использу€ конструктор класса LinkedListTabulatedFunction
        // и передава€ в него объект типа ConstantFunction, значени€ начала и конца интервала, а также количество точек на интервале
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1),1,1000,1000);
        Runnable readTask = new ReadTask(function);
        Runnable writeTask = new WriteTask(function,0.5);
        Thread threadRead = new Thread(readTask);
        Thread threadWrite = new Thread(writeTask);
        threadRead.start(); // запускаем поток threadRead
        threadWrite.start(); // запускаем поток threadWrite
    }
}