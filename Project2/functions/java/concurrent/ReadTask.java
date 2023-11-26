package concurrent;
import packFunctions.TabulatedFunction;

public class ReadTask implements Runnable {
    private final TabulatedFunction function;

    // конструктор класса, принимающий на вход объект типа TabulatedFunction
    public ReadTask(TabulatedFunction tabulatedFunction) {
        this.function = tabulatedFunction;

    }

    public void run() {
        // проходимся по всем элементам функции
        for (int i = 0; i < function.getCount(); i++) {
            // синхронизируем доступ к функции
            synchronized (function) {
                // формируем строку для вывода на экран
                String str = String.format("After read: i = %d, x = %f, y = %f", i, function.getX(i), function.getY(i));
                System.out.println(str);
            }
        }
    }

}