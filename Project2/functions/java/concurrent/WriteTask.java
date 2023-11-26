package concurrent;
import packFunctions.TabulatedFunction;

public class WriteTask implements Runnable {
    private TabulatedFunction function;
    private double value;

    // конструктор класса, принимающий на вход объект типа TabulatedFunction и значение типа double
    public WriteTask(TabulatedFunction tabulatedFunction, double value) {
        this.function = tabulatedFunction;
        this.value = value;
    }

    public void run() {
        // проходимся по всем элементам функции
        for (int i = 0; i < function.getCount(); i++) {
            function.setY(i, value);  // устанавливаем значение value для y-координаты элемента с индексом i
            String s = String.format("Writing for index %d complete ", i);
            System.out.println(s);
        }
    }

}